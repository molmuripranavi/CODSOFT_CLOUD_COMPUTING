package com.codesoft.cloud_file_storage.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;
import com.codesoft.cloud_file_storage.dto.FileInfo;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import com.codesoft.cloud_file_storage.dto.StorageInfo;

@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = {
    "http://localhost:5173",
    "https://codsoft-cloud-computing-bbrtagwud-molmuripranavis-projects.vercel.app"
})
public class FileController {

    private final Path uploadPath = Paths.get("uploads");

    public FileController() throws IOException {
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
    }

    // Upload Multiple Files
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFiles(
            @RequestParam("file") MultipartFile[] files) {

        if (files == null || files.length == 0) {
            return ResponseEntity.badRequest().body("No files selected.");
        }

        StringBuilder uploadedFiles = new StringBuilder();

        try {

            for (MultipartFile file : files) {

                if (!file.isEmpty()) {

                    Path filePath = uploadPath.resolve(file.getOriginalFilename());

                    Files.copy(
                            file.getInputStream(),
                            filePath,
                            StandardCopyOption.REPLACE_EXISTING
                    );

                    uploadedFiles.append(file.getOriginalFilename()).append("\n");
                }
            }

            return ResponseEntity.ok(
                    "Files uploaded successfully:\n" + uploadedFiles
            );

        } catch (IOException e) {

            return ResponseEntity.internalServerError()
                    .body("Upload failed: " + e.getMessage());

        }
    }

    // List All Files
   @GetMapping
public ResponseEntity<List<FileInfo>> listFiles() {

    try {

        DecimalFormat df = new DecimalFormat("#.##");

        List<FileInfo> files = Files.list(uploadPath)
                .map(path -> {

                    try {

                        String fileName = path.getFileName().toString();

                        long bytes = Files.size(path);

                        String size;

                        if (bytes >= 1024 * 1024) {
                            size = df.format(bytes / (1024.0 * 1024.0)) + " MB";
                        } else if (bytes >= 1024) {
                            size = df.format(bytes / 1024.0) + " KB";
                        } else {
                            size = bytes + " Bytes";
                        }

                        String extension = "";

                        int dotIndex = fileName.lastIndexOf(".");

                        if (dotIndex != -1) {
                            extension = fileName.substring(dotIndex + 1).toUpperCase();
                        }

                        Date lastModified =
                                new Date(Files.getLastModifiedTime(path).toMillis());

                        String uploadedDate =
                                new SimpleDateFormat("dd MMM yyyy")
                                        .format(lastModified);

                        String downloadUrl =
                                "/api/files/download/" + fileName;

                        return new FileInfo(
                                fileName,
                                size,
                                extension,
                                uploadedDate,
                                downloadUrl
                        );

                    } catch (IOException e) {

                        return null;

                    }

                })
                .filter(file -> file != null)
                .collect(Collectors.toList());

        return ResponseEntity.ok(files);

    } catch (IOException e) {

        return ResponseEntity.internalServerError().build();

    }
}
@GetMapping("/storage")
public ResponseEntity<StorageInfo> getStorageInfo() {

    try {

        long totalBytes = Files.list(uploadPath)
                .filter(Files::isRegularFile)
                .mapToLong(path -> {
                    try {
                        return Files.size(path);
                    } catch (IOException e) {
                        return 0;
                    }
                })
                .sum();

        int totalFiles = (int) Files.list(uploadPath).count();

        DecimalFormat df = new DecimalFormat("#.##");

        String totalSize;

        if (totalBytes >= 1024 * 1024) {
            totalSize = df.format(totalBytes / (1024.0 * 1024.0)) + " MB";
        } else if (totalBytes >= 1024) {
            totalSize = df.format(totalBytes / 1024.0) + " KB";
        } else {
            totalSize = totalBytes + " Bytes";
        }

        StorageInfo info =
                new StorageInfo(
                        totalFiles,
                        totalSize,
                        totalBytes
                );

        return ResponseEntity.ok(info);

    } catch (IOException e) {

        return ResponseEntity.internalServerError().build();

    }

}

    // Download Status
    @GetMapping("/download/status/{filename}")
    public ResponseEntity<String> downloadStatus(
            @PathVariable String filename) {

        Path file = uploadPath.resolve(filename);

        if (Files.exists(file)) {
            return ResponseEntity.ok("File downloaded successfully.");
        } else {
            return ResponseEntity.status(404).body("File not found.");
        }
    }

    // Download Actual File
    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFile(
            @PathVariable String filename)
            throws MalformedURLException {

        Path file = uploadPath.resolve(filename);

        Resource resource = new UrlResource(file.toUri());

        if (!resource.exists() || !resource.isReadable()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + filename + "\"")
                .body(resource);
    }

    // Delete File
    @DeleteMapping("/{filename}")
    public ResponseEntity<String> deleteFile(
            @PathVariable String filename) {

        try {

            Path file = uploadPath.resolve(filename);

            if (Files.deleteIfExists(file)) {
                return ResponseEntity.ok("File deleted successfully.");
            } else {
                return ResponseEntity.status(404)
                        .body("File not found.");
            }

        } catch (IOException e) {

            return ResponseEntity.internalServerError()
                    .body("Unable to delete file.");

        }
    }
   @GetMapping("/view/{filename:.+}")
public ResponseEntity<Resource> viewFile(
        @PathVariable String filename)
        throws IOException {

    Path file = uploadPath.resolve(filename);

    Resource resource = new UrlResource(file.toUri());

    if (!resource.exists() || !resource.isReadable()) {
        return ResponseEntity.notFound().build();
    }

    MediaType mediaType = MediaTypeFactory
            .getMediaType(resource)
            .orElse(MediaType.APPLICATION_OCTET_STREAM);

    return ResponseEntity.ok()
            .contentType(mediaType)
            .body(resource);
}
}

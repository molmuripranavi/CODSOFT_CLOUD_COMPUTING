package com.codesoft.cloud_file_storage.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/files")
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
    public ResponseEntity<List<String>> listFiles() {

        try {

            List<String> files = Files.list(uploadPath)
                    .map(path -> path.getFileName().toString())
                    .collect(Collectors.toList());

            return ResponseEntity.ok(files);

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
}
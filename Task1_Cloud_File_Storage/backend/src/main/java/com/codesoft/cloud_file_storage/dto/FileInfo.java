package com.codesoft.cloud_file_storage.dto;

public class FileInfo {

    private String name;
    private String size;
    private String type;
    private String uploadedDate;
    private String downloadUrl;

    public FileInfo() {
    }

    public FileInfo(String name,
                    String size,
                    String type,
                    String uploadedDate,
                    String downloadUrl) {

        this.name = name;
        this.size = size;
        this.type = type;
        this.uploadedDate = uploadedDate;
        this.downloadUrl = downloadUrl;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public String getUploadedDate() {
        return uploadedDate;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUploadedDate(String uploadedDate) {
        this.uploadedDate = uploadedDate;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
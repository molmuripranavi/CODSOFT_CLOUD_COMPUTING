package com.codesoft.cloud_file_storage.dto;

public class StorageInfo {

    private int totalFiles;
    private String totalSize;
    private long totalBytes;

    public StorageInfo() {}

    public StorageInfo(int totalFiles,
                       String totalSize,
                       long totalBytes) {
        this.totalFiles = totalFiles;
        this.totalSize = totalSize;
        this.totalBytes = totalBytes;
    }

    public int getTotalFiles() {
        return totalFiles;
    }

    public String getTotalSize() {
        return totalSize;
    }

    public long getTotalBytes() {
        return totalBytes;
    }

    public void setTotalFiles(int totalFiles) {
        this.totalFiles = totalFiles;
    }

    public void setTotalSize(String totalSize) {
        this.totalSize = totalSize;
    }

    public void setTotalBytes(long totalBytes) {
        this.totalBytes = totalBytes;
    }
}
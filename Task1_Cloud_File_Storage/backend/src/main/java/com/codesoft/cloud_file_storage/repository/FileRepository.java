package com.codesoft.cloud_file_storage.repository;

import com.codesoft.cloud_file_storage.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {
}
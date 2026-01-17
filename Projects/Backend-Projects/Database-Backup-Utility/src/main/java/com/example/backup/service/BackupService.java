package com.example.backup.service;

import com.example.backup.model.BackupRecord;
import com.example.backup.model.BackupRequest;
import com.example.backup.model.RestoreRequest;
import com.example.backup.repository.BackupRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class BackupService {
    
    @Autowired
    private BackupRecordRepository backupRecordRepository;
    
    @Value("${backup.storage.path:./backups}")
    private String backupStoragePath;
    
    /**
     * Create a database backup
     */
    public BackupRecord createBackup(BackupRequest request) {
        BackupRecord record = new BackupRecord();
        record.setBackupName(request.getBackupName() != null ? 
            request.getBackupName() : generateBackupName(request.getDatabaseName()));
        record.setDatabaseType(request.getDatabaseType());
        record.setDatabaseName(request.getDatabaseName());
        record.setStatus("IN_PROGRESS");
        
        record = backupRecordRepository.save(record);
        
        // Perform backup asynchronously
        CompletableFuture.runAsync(() -> {
            try {
                performBackup(request, record);
            } catch (Exception e) {
                record.setStatus("FAILED");
                record.setErrorMessage(e.getMessage());
                backupRecordRepository.save(record);
            }
        });
        
        return record;
    }
    
    /**
     * Perform the actual backup operation
     */
    private void performBackup(BackupRequest request, BackupRecord record) throws Exception {
        String backupFileName = record.getBackupName() + ".sql";
        Path backupDir = Paths.get(backupStoragePath, request.getDatabaseType().toLowerCase());
        Files.createDirectories(backupDir);
        Path backupPath = backupDir.resolve(backupFileName);
        
        ProcessBuilder processBuilder = createBackupProcess(request, backupPath);
        Process process = processBuilder.start();
        
        // Read error stream
        try (BufferedReader errorReader = new BufferedReader(
                new InputStreamReader(process.getErrorStream()))) {
            StringBuilder errorOutput = new StringBuilder();
            String line;
            while ((line = errorReader.readLine()) != null) {
                errorOutput.append(line).append("\n");
            }
            
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new Exception("Backup failed: " + errorOutput.toString());
            }
        }
        
        // Update record with success
        record.setFilePath(backupPath.toString());
        record.setFileSize(Files.size(backupPath));
        record.setStatus("SUCCESS");
        backupRecordRepository.save(record);
    }
    
    /**
     * Create backup process based on database type
     */
    private ProcessBuilder createBackupProcess(BackupRequest request, Path backupPath) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        
        if ("POSTGRESQL".equalsIgnoreCase(request.getDatabaseType())) {
            processBuilder.command(
                "pg_dump",
                "-h", request.getHost(),
                "-p", String.valueOf(request.getPort()),
                "-U", request.getUsername(),
                "-d", request.getDatabaseName(),
                "-f", backupPath.toString()
            );
            processBuilder.environment().put("PGPASSWORD", request.getPassword());
        } else if ("MYSQL".equalsIgnoreCase(request.getDatabaseType())) {
            processBuilder.command(
                "mysqldump",
                "-h", request.getHost(),
                "-P", String.valueOf(request.getPort()),
                "-u", request.getUsername(),
                "-p" + request.getPassword(),
                request.getDatabaseName()
            );
            processBuilder.redirectOutput(backupPath.toFile());
        }
        
        return processBuilder;
    }
    
    /**
     * Restore database from backup
     */
    public void restoreBackup(Long backupId, RestoreRequest restoreRequest) throws Exception {
        BackupRecord backup = backupRecordRepository.findById(backupId)
            .orElseThrow(() -> new Exception("Backup not found"));
        
        if (!"SUCCESS".equals(backup.getStatus())) {
            throw new Exception("Backup is not in a valid state for restoration");
        }
        
        Path backupPath = Paths.get(backup.getFilePath());
        if (!Files.exists(backupPath)) {
            throw new Exception("Backup file not found");
        }
        
        ProcessBuilder processBuilder = createRestoreProcess(backup, restoreRequest, backupPath);
        Process process = processBuilder.start();
        
        try (BufferedReader errorReader = new BufferedReader(
                new InputStreamReader(process.getErrorStream()))) {
            StringBuilder errorOutput = new StringBuilder();
            String line;
            while ((line = errorReader.readLine()) != null) {
                errorOutput.append(line).append("\n");
            }
            
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new Exception("Restore failed: " + errorOutput.toString());
            }
        }
    }
    
    /**
     * Create restore process based on database type
     */
    private ProcessBuilder createRestoreProcess(BackupRecord backup, 
                                                RestoreRequest request, 
                                                Path backupPath) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        
        if ("POSTGRESQL".equalsIgnoreCase(backup.getDatabaseType())) {
            processBuilder.command(
                "psql",
                "-h", request.getTargetHost(),
                "-p", String.valueOf(request.getTargetPort()),
                "-U", request.getTargetUsername(),
                "-d", request.getTargetDatabase(),
                "-f", backupPath.toString()
            );
            processBuilder.environment().put("PGPASSWORD", request.getTargetPassword());
        } else if ("MYSQL".equalsIgnoreCase(backup.getDatabaseType())) {
            processBuilder.command(
                "mysql",
                "-h", request.getTargetHost(),
                "-P", String.valueOf(request.getTargetPort()),
                "-u", request.getTargetUsername(),
                "-p" + request.getTargetPassword(),
                request.getTargetDatabase()
            );
            processBuilder.redirectInput(backupPath.toFile());
        }
        
        return processBuilder;
    }
    
    /**
     * Validate backup integrity
     */
    public boolean validateBackup(Long backupId) {
        BackupRecord backup = backupRecordRepository.findById(backupId).orElse(null);
        if (backup == null || !"SUCCESS".equals(backup.getStatus())) {
            return false;
        }
        
        Path backupPath = Paths.get(backup.getFilePath());
        return Files.exists(backupPath) && Files.size(backupPath) > 0;
    }
    
    /**
     * Get all backups
     */
    public List<BackupRecord> getAllBackups() {
        return backupRecordRepository.findAllByOrderByCreatedAtDesc();
    }
    
    /**
     * Get backup by ID
     */
    public BackupRecord getBackupById(Long id) {
        return backupRecordRepository.findById(id).orElse(null);
    }
    
    /**
     * Delete backup
     */
    public void deleteBackup(Long id) throws Exception {
        BackupRecord backup = backupRecordRepository.findById(id)
            .orElseThrow(() -> new Exception("Backup not found"));
        
        if (backup.getFilePath() != null) {
            Path backupPath = Paths.get(backup.getFilePath());
            Files.deleteIfExists(backupPath);
        }
        
        backupRecordRepository.delete(backup);
    }
    
    /**
     * Generate backup name with timestamp
     */
    private String generateBackupName(String databaseName) {
        String timestamp = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        return databaseName + "_" + timestamp;
    }
}

package com.example.backup.controller;

import com.example.backup.model.BackupRecord;
import com.example.backup.model.BackupRequest;
import com.example.backup.model.RestoreRequest;
import com.example.backup.service.BackupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class BackupController {
    
    @Autowired
    private BackupService backupService;
    
    @PostMapping("/backup")
    public ResponseEntity<BackupRecord> createBackup(@RequestBody BackupRequest request) {
        BackupRecord record = backupService.createBackup(request);
        return ResponseEntity.ok(record);
    }
    
    @GetMapping("/backups")
    public ResponseEntity<List<BackupRecord>> getAllBackups() {
        List<BackupRecord> backups = backupService.getAllBackups();
        return ResponseEntity.ok(backups);
    }
    
    @GetMapping("/backups/{id}")
    public ResponseEntity<BackupRecord> getBackup(@PathVariable Long id) {
        BackupRecord backup = backupService.getBackupById(id);
        if (backup != null) {
            return ResponseEntity.ok(backup);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping("/backups/{id}/restore")
    public ResponseEntity<String> restoreBackup(
            @PathVariable Long id,
            @RequestBody RestoreRequest request) {
        try {
            backupService.restoreBackup(id, request);
            return ResponseEntity.ok("Backup restored successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Restore failed: " + e.getMessage());
        }
    }
    
    @GetMapping("/backups/validate/{id}")
    public ResponseEntity<Boolean> validateBackup(@PathVariable Long id) {
        boolean isValid = backupService.validateBackup(id);
        return ResponseEntity.ok(isValid);
    }
    
    @DeleteMapping("/backups/{id}")
    public ResponseEntity<String> deleteBackup(@PathVariable Long id) {
        try {
            backupService.deleteBackup(id);
            return ResponseEntity.ok("Backup deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Delete failed: " + e.getMessage());
        }
    }
}

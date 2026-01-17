package com.example.backup.scheduler;

import com.example.backup.model.BackupRequest;
import com.example.backup.service.BackupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BackupScheduler {
    
    @Autowired
    private BackupService backupService;
    
    @Value("${backup.schedule.enabled:false}")
    private boolean scheduleEnabled;
    
    @Value("${backup.schedule.database.type:POSTGRESQL}")
    private String databaseType;
    
    @Value("${backup.schedule.database.host:localhost}")
    private String host;
    
    @Value("${backup.schedule.database.port:5432}")
    private Integer port;
    
    @Value("${backup.schedule.database.name:}")
    private String databaseName;
    
    @Value("${backup.schedule.database.username:}")
    private String username;
    
    @Value("${backup.schedule.database.password:}")
    private String password;
    
    /**
     * Scheduled backup - runs daily at 2 AM by default
     * Configure cron expression in application.properties
     */
    @Scheduled(cron = "${backup.schedule.cron:0 0 2 * * ?}")
    public void scheduledBackup() {
        if (!scheduleEnabled || databaseName.isEmpty()) {
            return;
        }
        
        BackupRequest request = new BackupRequest();
        request.setDatabaseType(databaseType);
        request.setHost(host);
        request.setPort(port);
        request.setDatabaseName(databaseName);
        request.setUsername(username);
        request.setPassword(password);
        
        backupService.createBackup(request);
    }
}

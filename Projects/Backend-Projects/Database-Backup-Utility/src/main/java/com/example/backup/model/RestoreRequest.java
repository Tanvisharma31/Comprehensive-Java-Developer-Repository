package com.example.backup.model;

public class RestoreRequest {
    private String targetHost;
    private Integer targetPort;
    private String targetDatabase;
    private String targetUsername;
    private String targetPassword;
    
    // Getters and Setters
    public String getTargetHost() {
        return targetHost;
    }
    
    public void setTargetHost(String targetHost) {
        this.targetHost = targetHost;
    }
    
    public Integer getTargetPort() {
        return targetPort;
    }
    
    public void setTargetPort(Integer targetPort) {
        this.targetPort = targetPort;
    }
    
    public String getTargetDatabase() {
        return targetDatabase;
    }
    
    public void setTargetDatabase(String targetDatabase) {
        this.targetDatabase = targetDatabase;
    }
    
    public String getTargetUsername() {
        return targetUsername;
    }
    
    public void setTargetUsername(String targetUsername) {
        this.targetUsername = targetUsername;
    }
    
    public String getTargetPassword() {
        return targetPassword;
    }
    
    public void setTargetPassword(String targetPassword) {
        this.targetPassword = targetPassword;
    }
}

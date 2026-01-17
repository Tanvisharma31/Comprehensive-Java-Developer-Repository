package com.example.leaderboard.model;

public class LeaderboardEntry {
    private String userId;
    private String username;
    private Long score;
    private Integer rank;
    
    public LeaderboardEntry() {}
    
    public LeaderboardEntry(String userId, String username, Long score, Integer rank) {
        this.userId = userId;
        this.username = username;
        this.score = score;
        this.rank = rank;
    }
    
    // Getters and Setters
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public Long getScore() {
        return score;
    }
    
    public void setScore(Long score) {
        this.score = score;
    }
    
    public Integer getRank() {
        return rank;
    }
    
    public void setRank(Integer rank) {
        this.rank = rank;
    }
}

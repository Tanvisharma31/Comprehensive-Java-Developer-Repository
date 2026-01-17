package com.example.leaderboard.controller;

import com.example.leaderboard.model.LeaderboardEntry;
import com.example.leaderboard.model.Score;
import com.example.leaderboard.service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LeaderboardController {
    
    @Autowired
    private LeaderboardService leaderboardService;
    
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    
    @PostMapping("/scores")
    public ResponseEntity<Score> submitScore(@RequestBody Score score) {
        Score saved = leaderboardService.submitScore(score);
        
        // Broadcast updated leaderboard to all WebSocket clients
        List<LeaderboardEntry> topPlayers = leaderboardService.getTopPlayers(10);
        messagingTemplate.convertAndSend("/topic/leaderboard", topPlayers);
        
        return ResponseEntity.ok(saved);
    }
    
    @GetMapping("/leaderboard")
    public ResponseEntity<List<LeaderboardEntry>> getLeaderboard(
            @RequestParam(defaultValue = "10") int limit) {
        List<LeaderboardEntry> leaderboard = leaderboardService.getTopPlayers(limit);
        return ResponseEntity.ok(leaderboard);
    }
    
    @GetMapping("/leaderboard/{userId}")
    public ResponseEntity<LeaderboardEntry> getUserRank(@PathVariable String userId) {
        LeaderboardEntry entry = leaderboardService.getUserRank(userId);
        if (entry != null) {
            return ResponseEntity.ok(entry);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/scores/{userId}")
    public ResponseEntity<List<Score>> getUserScoreHistory(@PathVariable String userId) {
        List<Score> history = leaderboardService.getUserScoreHistory(userId);
        return ResponseEntity.ok(history);
    }
}

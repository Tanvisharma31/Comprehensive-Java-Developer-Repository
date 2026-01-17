package com.example.leaderboard.service;

import com.example.leaderboard.model.LeaderboardEntry;
import com.example.leaderboard.model.Score;
import com.example.leaderboard.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class LeaderboardService {
    
    private static final String LEADERBOARD_KEY = "leaderboard:scores";
    
    @Autowired
    private ScoreRepository scoreRepository;
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    /**
     * Submit a new score and update leaderboard
     */
    public Score submitScore(Score score) {
        Score saved = scoreRepository.save(score);
        
        // Update Redis sorted set for fast leaderboard queries
        ZSetOperations<String, String> zSetOps = redisTemplate.opsForZSet();
        String member = saved.getUserId() + ":" + saved.getUsername();
        zSetOps.add(LEADERBOARD_KEY, member, saved.getScore().doubleValue());
        
        return saved;
    }
    
    /**
     * Get top N players from leaderboard
     */
    public List<LeaderboardEntry> getTopPlayers(int limit) {
        ZSetOperations<String, String> zSetOps = redisTemplate.opsForZSet();
        Set<ZSetOperations.TypedTuple<String>> topScores = 
            zSetOps.reverseRangeWithScores(LEADERBOARD_KEY, 0, limit - 1);
        
        List<LeaderboardEntry> leaderboard = new ArrayList<>();
        int rank = 1;
        
        for (ZSetOperations.TypedTuple<String> tuple : topScores) {
            String member = tuple.getValue();
            String[] parts = member.split(":", 2);
            String userId = parts[0];
            String username = parts.length > 1 ? parts[1] : userId;
            Long score = tuple.getScore().longValue();
            
            leaderboard.add(new LeaderboardEntry(userId, username, score, rank++));
        }
        
        return leaderboard;
    }
    
    /**
     * Get user's rank in leaderboard
     */
    public LeaderboardEntry getUserRank(String userId) {
        ZSetOperations<String, String> zSetOps = redisTemplate.opsForZSet();
        
        // Find user's score
        Set<ZSetOperations.TypedTuple<String>> allScores = 
            zSetOps.reverseRangeWithScores(LEADERBOARD_KEY, 0, -1);
        
        int rank = 1;
        for (ZSetOperations.TypedTuple<String> tuple : allScores) {
            String member = tuple.getValue();
            if (member.startsWith(userId + ":")) {
                String[] parts = member.split(":", 2);
                String username = parts.length > 1 ? parts[1] : userId;
                Long score = tuple.getScore().longValue();
                return new LeaderboardEntry(userId, username, score, rank);
            }
            rank++;
        }
        
        return null;
    }
    
    /**
     * Get user's score history
     */
    public List<Score> getUserScoreHistory(String userId) {
        return scoreRepository.findByUserIdOrderByScoreDesc(userId);
    }
}

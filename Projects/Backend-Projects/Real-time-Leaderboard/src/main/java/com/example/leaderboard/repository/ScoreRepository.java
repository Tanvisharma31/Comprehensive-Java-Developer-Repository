package com.example.leaderboard.repository;

import com.example.leaderboard.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findByUserIdOrderByScoreDesc(String userId);
    
    @Query("SELECT s FROM Score s ORDER BY s.score DESC, s.timestamp ASC")
    List<Score> findAllOrderedByScore();
    
    Optional<Score> findTopByUserIdOrderByScoreDesc(String userId);
}

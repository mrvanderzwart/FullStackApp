package com.mikerofone.footballdashboard.repository;

import com.mikerofone.footballdashboard.model.Matches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Matches,Integer> {
    
}

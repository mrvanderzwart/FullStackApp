package com.mikerofone.footballdashboard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mikerofone.footballdashboard.model.Matches;
import com.mikerofone.footballdashboard.repository.MatchRepository;
import com.mikerofone.footballdashboard.scraper.Scraper;

@Service
public class MatchServiceImplementation implements MatchService{

    @Autowired
    private MatchRepository matchRepository;
    
    @Override
    public Matches saveMatch(Matches match) {
        return matchRepository.save(match);
    }

    @Override
    public List<Matches> getAllMatches() {
        return matchRepository.findAll();
    }

    @Override
    public List<Integer> getResults() {
        List<Matches> matches = matchRepository.findAll();
        
        return Scraper.getResults(matches);
    }

    @Override
    public List<Integer> getMatchLabels() {
        List<Matches> matches = matchRepository.findAll();
        List<Integer> matchLabels = new ArrayList<>();
        int count = 1;
        for (int i = 0; i < matches.size(); i++) {
            if (matches.get(i).getCompetition().equals("Eredivisie")) {
                matchLabels.add(count);
                count++;
            }
        }

        return matchLabels;
    }

    @Override
    public void removeAllMatches() {
        matchRepository.deleteAll();
    }

    @Override
    public void parseAllMatches() {
        Scraper.start(matchRepository);
    }
}
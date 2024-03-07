package com.mikerofone.footballdashboard.service;

import java.util.List;

import com.mikerofone.footballdashboard.model.Matches;

public interface MatchService {
    public Matches saveMatch(Matches student);
    public List<Matches> getAllMatches();
    public List<Integer> getResults();
    public List<Integer> getMatchLabels();
    public void removeAllMatches();
    public void parseAllMatches();
}
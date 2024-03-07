package com.mikerofone.footballdashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mikerofone.footballdashboard.model.Matches;
import com.mikerofone.footballdashboard.service.MatchService;

@RestController
@RequestMapping("/match")
@CrossOrigin(origins="*")
public class MatchController {
    @Autowired
    private MatchService matchService;

    @PostMapping("/add")
    public String add(@RequestBody Matches match) {
        matchService.saveMatch(match);
        return "New match is added";
    }

    @GetMapping("/getAll")
    public List<Matches> getAllMatches() {
        return matchService.getAllMatches();
    }

    @GetMapping("/getResults")
    public List<Integer> getResults() {
        return matchService.getResults();
    }

    @GetMapping("/getMatchLabels")
    public List<Integer> getMatchLabels() {
        return matchService.getMatchLabels();
    }

    @PostMapping("/removeAll")
    public String removeAllMatches() {
        matchService.removeAllMatches();
        return "All matches removed";
    }

    @PostMapping("/parseMatches")
    public String parseAllMatches() {
        matchService.removeAllMatches();
        matchService.parseAllMatches();
        return "All matched parsed";
    }
}
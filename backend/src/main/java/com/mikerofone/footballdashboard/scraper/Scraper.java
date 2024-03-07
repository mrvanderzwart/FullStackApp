
package com.mikerofone.footballdashboard.scraper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mikerofone.footballdashboard.model.Matches;
import com.mikerofone.footballdashboard.repository.MatchRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.text.ParseException;

public class Scraper {

    public static List<Integer> getResults(List<Matches> matches) {
        List<Integer> results = new ArrayList<>();
        int points = 0;
        for (Matches match : matches) {
            if (!match.getCompetition().equals("Eredivisie")) {
                continue;
            }
            if (match.getResult().equals("W")) {
                points += 3;
            } else if (match.getResult().equals("D")) {
                points += 1;
            }

            results.add(points);
        }

        return results;
    }

    public static void start(MatchRepository matchRepository) {

        String url = "https://fbref.com/en/squads/19c3f8c4/Ajax-Stats";

        try {
            // Make an HTTP GET request to the URL
            Document document = Jsoup.connect(url).get();

            // Find the table containing match results
            Element table = document.select("#all_matchlogs").first();

            // Extract rows from the table
            Elements rows = table.select("tbody tr");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date today = new Date();
            Date match_date = new Date();
            // Loop through the rows and extract match results
            for (Element row : rows) {
                String date = row.select("th[data-stat=date]").text();
                String opponent = row.select("td[data-stat=opponent]").text();
                String result = row.select("td[data-stat=result]").text();
                String competition = row.select("td[data-stat=comp]").text();

                try {
                    match_date = dateFormat.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (today.before(match_date)) 
                    break;

                Matches newMatch = new Matches();

                newMatch.setDate(date);
                newMatch.setOpponent(opponent);
                newMatch.setResult(result);
                newMatch.setCompetition(competition);

                matchRepository.save(newMatch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }
}
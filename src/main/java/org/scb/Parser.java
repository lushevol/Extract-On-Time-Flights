package org.scb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private String rawHtml;
    Parser(String in) {
        rawHtml = in;
    }
    public List<String[]> run() {
        List<String[]> result = new ArrayList<>();
        Document doc = Jsoup.parse(rawHtml);
        Elements flightRows = doc.select("div.flight-row");
        for (Element flight: flightRows) {
            Elements cols = flight.select("div.flight-col:not(.flight-col__terminal-mob)");
            List<String> colList = new ArrayList<>();
            for (Element col: cols) {
                colList.add(col.text());
            }
            if (colList.size() > 0 && isFlightOnTime(colList.get(colList.size()-1))) {
                result.add(colList.toArray(new String[0]));
            }
        }
        System.out.println("[Parser] Finished parse the content and extract validate rows");
        return result;
    }

    private boolean isFlightOnTime(String status) {
        return status.equals("Status") || status.matches("Landed.*On-time.*");
    }
}

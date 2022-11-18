package org.scb;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        String url = "https://www.tianjin-airport.com/departures.php";
        // to download html of target website.
        String html = new Spider(url).run();
        // parse the content and extract validate rows
        List<String[]> csvContent = new Parser(html).run();
        // write to csv file
        CsvSaver cs = new CsvSaver("flights-on-time.csv");
        cs.save(csvContent);
    }
}
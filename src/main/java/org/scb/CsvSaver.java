package org.scb;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvSaver {
    private String path = "./";
    private String relativeFilePath;
    private CSVWriter writer;
    public CsvSaver(String fileName) throws IOException {
        relativeFilePath = path + fileName;
        writer = new CSVWriter(new FileWriter(relativeFilePath));
    }
    public void save(List<String[]> content) throws Exception {
        System.out.println("[CSV Writer] Ready to write to file");
        writer.writeAll(content);
        writer.flush();
        System.out.println("[CSV Writer] Finished to write to file");
    }
}

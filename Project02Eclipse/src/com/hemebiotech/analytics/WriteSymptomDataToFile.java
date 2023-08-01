package com.hemebiotech.analytics;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class WriteSymptomDataToFile implements ISymptomWriter {
    private Map<String, Integer> filepath;

    public WriteSymptomDataToFile(Map<String, Integer> symptoms) {
        this.filepath = symptoms;
    }

    @Override
    public void writeSymptoms() throws IOException {
        TreeMap<String, Integer> results = new TreeMap<String, Integer>();
        results.putAll(filepath);

        BufferedWriter writer = new BufferedWriter(new FileWriter ("result.out"));
            try {
                for (Map.Entry<String, Integer> entry : results.entrySet()) {
                    writer.write(entry.getKey() + ' : ' + entry.getValue());
                    writer.newLine();
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
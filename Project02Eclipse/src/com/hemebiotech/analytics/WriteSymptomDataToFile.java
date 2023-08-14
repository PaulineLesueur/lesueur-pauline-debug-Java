package com.hemebiotech.analytics;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class WriteSymptomDataToFile implements ISymptomWriter {
    private String filepath;

    /**
	 * 
	 * @param filepath a full or partial path to the Map with the symptoms and their occurrences
	 */
    public WriteSymptomDataToFile(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public void writeSymptoms(Map<String, Integer> results) throws IOException {
        /**
		 * {@inheritDoc}
		 */
        BufferedWriter writer = new BufferedWriter(new FileWriter (filepath));
            try {
                for (Map.Entry<String, Integer> entry : results.entrySet()) {
                    writer.write(entry.getKey() + " : " + entry.getValue());
                    writer.newLine();
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
                writer.close();
            }
    }
}
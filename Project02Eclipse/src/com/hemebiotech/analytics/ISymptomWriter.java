package com.hemebiotech.analytics;

import java.io.IOException;
import java.util.Map;

/**
 * Write the content of a map in a new file, line by line
 */
public interface ISymptomWriter {
    /**
     * create a new map, puts the input map in the new one and write the content in the new file created
     * 
     * @throws IOException
     */
    public void writeSymptoms(Map<String, Integer> results) throws IOException;
} 
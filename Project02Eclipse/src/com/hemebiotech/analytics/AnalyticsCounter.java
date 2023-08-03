package com.hemebiotech.analytics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AnalyticsCounter {
	private ISymptomReader reader;
	private ISymptomWriter writer;
	
	public AnalyticsCounter(ISymptomReader reader, ISymptomWriter writer) {
		this.reader = reader;
		this.writer = writer;
	}

	public List<String> getSymptoms() {
		return reader.getSymptoms();
	}

	public Map<String, Integer> countSymptoms(List<String> symptoms) {
		Map<String, Integer> symptomsCount = new TreeMap<>();
		for(String symptom : symptoms) {
			if(symptomsCount.containsKey(symptom)) {
				symptomsCount.put(symptom, symptomsCount.get(symptom)+1);
			} else {
				symptomsCount.put(symptom, 1);
			}
		}
		return symptomsCount;
	}

	public static void main(String args[]) throws Exception {
		ISymptomReader reader = new ReadSymptomDataFromFile("symptoms.txt");
		ISymptomWriter writer = null;

		AnalyticsCounter analyticsCounter = new AnalyticsCounter(reader, writer);
		List<String> symptomsList = analyticsCounter.getSymptoms();
		Map<String, Integer> symptomsCounted = analyticsCounter.countSymptoms(symptomsList);
		for (Map.Entry<String, Integer> pair : symptomsCounted.entrySet()) {
			System.out.println(String.format("%s : %s", pair.getKey(), pair.getValue()));   
		}
	}
	/*
	private static int headacheCount = 0;
	private static int rashCount = 0;
	private static int pupilCount = 0;
	
	public static void main(String args[]) throws Exception {
		// first get input
		BufferedReader reader = new BufferedReader (new FileReader("symptoms.txt"));
		String line = reader.readLine();

		int i = 0;
		int headCount = 0;
		while (line != null) {
			i++;
			System.out.println("symptom from file: " + line);
			if (line.equals("headache")) {
				headCount++;
				System.out.println("number of headaches: " + headCount);
			}
			else if (line.equals("rush")) {
				rashCount++;
			}
			else if (line.contains("pupils")) {
				pupilCount++;
			}

			line = reader.readLine();	// get another symptom
		}
		
		// next generate output
		FileWriter writer = new FileWriter ("result.out");
		writer.write("headache: " + headacheCount + "\n");
		writer.write("rash: " + rashCount + "\n");
		writer.write("dialated pupils: " + pupilCount + "\n");
		writer.close();
	}*/
}

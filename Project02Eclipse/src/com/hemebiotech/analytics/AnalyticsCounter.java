package com.hemebiotech.analytics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.io.IOException;

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

	public void writeSymptoms(Map<String, Integer> symptoms) {
		try {
			writer.writeSymptoms(symptoms);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws Exception {
		ISymptomReader reader = new ReadSymptomDataFromFile("symptoms.txt");
		ISymptomWriter writer = new WriteSymptomDataToFile("result.out");

		AnalyticsCounter analyticsCounter = new AnalyticsCounter(reader, writer);
		List<String> symptomsList = analyticsCounter.getSymptoms();
		Map<String, Integer> symptomsCounted = analyticsCounter.countSymptoms(symptomsList);
		for (Map.Entry<String, Integer> pair : symptomsCounted.entrySet()) {
			System.out.println(String.format("%s : %s", pair.getKey(), pair.getValue()));   
		}
		analyticsCounter.writeSymptoms(symptomsCounted);
	}
}

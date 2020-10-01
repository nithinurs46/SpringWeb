package com.springweb.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HomePageJsonGenerator {

	public HomePageJsonGenerator() {
	}

	private static final String BAR_CHART_SECTION_1 = "Section 1";
	private static final String BAR_CHART_SECTION_2 = "Section 2";
	private static final String BAR_CHART_SECTION_3 = "Section 3";
	private static final String BAR_CHART_SECTION_4 = "Section 4";
	private static final String BAR_CHART_SECTION_5 = "Section 5";
	private static final String BAR_CHART_SECTION_6 = "Section 6";

	private static final String PIE_CHART_SECTION_1 = "Section 1";
	private static final String PIE_CHART_SECTION_2 = "Section 2";
	private static final String PIE_CHART_SECTION_3 = "Section 3";
	private static final String PIE_CHART_SECTION_4 = "Section 4";
	private static final String PIE_CHART_SECTION_5 = "Section 5";
	private static final String PIE_CHART_SECTION_6 = "Section 6";

	public String generateJSONforBarChart() {

		List<String> barChart = new ArrayList<String>();
		barChart.add(BAR_CHART_SECTION_1);
		barChart.add(BAR_CHART_SECTION_2);
		barChart.add(BAR_CHART_SECTION_3);
		barChart.add(BAR_CHART_SECTION_4);
		barChart.add(BAR_CHART_SECTION_5);
		barChart.add(BAR_CHART_SECTION_6);

		List<Map<String, String>> jsonData = new ArrayList<Map<String, String>>();
		for (int i = 0; i < barChart.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			String section = barChart.get(i);
			getCountForBarChart(section, map);
			jsonData.add(map);
		}

		return getJson(jsonData);

	}

	public String generateJSONforPieChart() {

		List<String> pieChart = new ArrayList<String>();
		pieChart.add(PIE_CHART_SECTION_1);
		pieChart.add(PIE_CHART_SECTION_2);
		pieChart.add(PIE_CHART_SECTION_3);
		pieChart.add(PIE_CHART_SECTION_4);
		pieChart.add(PIE_CHART_SECTION_5);
		pieChart.add(PIE_CHART_SECTION_6);

		List<Map<String, String>> jsonData = new ArrayList<Map<String, String>>();
		for (int i = 0; i < pieChart.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			String section = pieChart.get(i);
			getCountForPieChart(section, map);
			jsonData.add(map);
		}

		return getJson(jsonData);

	}

	private void getCountForBarChart(String child, Map<String, String> map) {
		String count = null;
		String filter = null;
		if (BAR_CHART_SECTION_1.equals(child)) {
			count = "10";
			filter = "section1";
		} else if (BAR_CHART_SECTION_2.equals(child)) {
			count = "20";
			filter = "section2";
		} else if (BAR_CHART_SECTION_3.equals(child)) {
			count = "30";
			filter = "section3";
		} else if (BAR_CHART_SECTION_4.equals(child)) {
			count = "40";
			filter = "section4";
		} else if (BAR_CHART_SECTION_5.equals(child)) {
			count = "50";
			filter = "section5";
		} else if (BAR_CHART_SECTION_6.equals(child)) {
			count = "60";
			filter = "section6";
		}

		map.put("label", child);
		map.put("value", count);
		map.put("filter", filter);
	}

	private void getCountForPieChart(String child, Map<String, String> map) {
		String count = null;
		String filter = null;
		if (PIE_CHART_SECTION_1.equals(child)) {
			count = "10";
			filter = "section1";
		} else if (PIE_CHART_SECTION_2.equals(child)) {
			count = "20";
			filter = "section2";
		} else if (PIE_CHART_SECTION_3.equals(child)) {
			count = "30";
			filter = "section3";
		} else if (PIE_CHART_SECTION_4.equals(child)) {
			count = "40";
			filter = "section4";
		} else if (PIE_CHART_SECTION_5.equals(child)) {
			count = "50";
			filter = "section5";
		} else if (PIE_CHART_SECTION_6.equals(child)) {
			count = "60";
			filter = "section6";
		}

		map.put("label", child);
		map.put("value", count);
		map.put("filter", filter);
	}

	private String getJson(List<Map<String, String>> value) {
		String json = "";
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

}

package com.test;

import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileInputStream;
import java.io.FileReader;

public class TestDemo {

	public static void main(String[] args) {
		String homeDir = System.getProperty("user.home");
		String jsonPath = homeDir + "\\eclipse-workspace\\restAssuredApiAutomation\\src\\main\\resources\\response.json";

		readingJsonFileData(jsonPath);

	}
	// TODO Auto-generated method stub

	public static void readingJsonFileData(String jsonPath) {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(jsonPath));
			JSONObject jsonObject = (JSONObject) obj;
			String name = (String) jsonObject.get("name");
			String location = (String) jsonObject.get("location");
			JSONArray players = (JSONArray) jsonObject.get("player");
			System.out.println("Name: " + name);
			System.out.println("Location: " + location);
			testPlayersOrigin(players);
			validateWicketPlayer(players);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void testPlayersOrigin(JSONArray players) {
		int countryCount = 0;
		for (int i = 0; i < players.size(); i++) {
			JSONObject jo = (JSONObject) players.get(i);
			String country = (String) jo.get("country");
			if (!country.equalsIgnoreCase("India")) {
				countryCount++;
			}

		}
		if (countryCount == 4) {
			System.out.println("Foreign Player test case is passed!");
		}
	}

	public static void validateWicketPlayer(JSONArray players) {
		int roleCount = 0;
		for (int i = 0; i < players.size(); i++) {
			JSONObject jo = (JSONObject) players.get(i);
			String country = (String) jo.get("role");
			if (country.equalsIgnoreCase("Wicket-keeper")) {
				roleCount++;
			}

		}
		if (roleCount >= 1) {
			System.out.println("Wicket Keeper test case is passed!");
		}

	}
	
}

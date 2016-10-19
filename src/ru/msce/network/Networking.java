package ru.msce.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ru.msce.model.Day;
import ru.msce.model.Group;

public class Networking {
	public static ArrayList<String> getDatesByURL(String url) throws Exception {
		ArrayList<String> dates = new ArrayList<String>();
		
		String response = readUrl(url);
		JsonElement element = new JsonParser().parse(response);
		JsonObject obj = element.getAsJsonObject();
		for (JsonElement date : obj.getAsJsonObject("data").getAsJsonArray("dates")) {
			dates.add(date.getAsString());
		}

		return dates;
	}

	public static Group getGroupByURL(String url) throws Exception {
		Gson gson = new Gson();
		String response = readUrl(url);
		if (isOk(response)) {
			response = getData(response);
			return gson.fromJson(response, Group.class);
		} else
			throw new NullPointerException(getErrorMessage(response));
	}

	private static String getErrorMessage(String json) {
		JsonElement element = new JsonParser().parse(json);
		JsonObject obj = element.getAsJsonObject();
		return obj.get("message").toString();
	}

	private static boolean isOk(String json) {
		JsonElement element = new JsonParser().parse(json);
		JsonObject obj = element.getAsJsonObject();
		if (obj.get("code").getAsInt() == 0)
			return true;
		return false;
	}

	public static Day getDayByURL(String url) throws Exception {

		Gson gson = new Gson();
		String response = readUrl(url);
		if (isOk(response)) {
			response = getData(response);
			return gson.fromJson(response, Day.class);
		} else
			throw new NullPointerException(getErrorMessage(response));
	}

	private static String getData(String json) {
		JsonElement element = new JsonParser().parse(json);
		JsonObject obj = element.getAsJsonObject();
		return obj.getAsJsonObject("data").toString();
	}

	private static String readUrl(String urlString) throws Exception {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);

			return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}
}

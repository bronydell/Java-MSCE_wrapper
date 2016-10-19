package network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import model.Day;
import model.Group;

public class MSCE {

	private static String baseURL = "http://s1.al3xable.me/method/";

	public static Day getStudent() throws JsonSyntaxException, Exception {
		Gson gson = new Gson();
		String response = readUrl(baseURL + "getStudent");
		if(isOk(response)){
			response = getData(response);
			return gson.fromJson(response, Day.class);
		}
		else
			throw new NullPointerException(getErrorMessage(response)); 
	}

	public static Day getStudent(String date) throws JsonSyntaxException, Exception {
		Gson gson = new Gson();
		String response = readUrl(baseURL + "getStudent?date=" + date);
		if(isOk(response)){
			response = getData(response);
			return gson.fromJson(response, Day.class);
		}
		else
			throw new NullPointerException(getErrorMessage(response)); 
	}

	public static Group getGroup(String group) throws JsonSyntaxException, Exception {
		Gson gson = new Gson();
		String response = readUrl(baseURL + "getStudent?group=" + group);
		if(isOk(response)){
			response = getData(response);
			return gson.fromJson(response, Group.class);
		}
		else
			throw new NullPointerException(getErrorMessage(response)); 
		
	}

	public static Group getGroup(String group, String date) throws JsonSyntaxException, Exception {
		Gson gson = new Gson();
		String response = readUrl(baseURL + "getStudent?group=" + group + "&date = " + date);
		if(isOk(response)){
			response = getData(response);
			return gson.fromJson(response, Group.class);
		}
		else
			throw new NullPointerException(getErrorMessage(response));
	}

	public static ArrayList<String> getTeacherDates() throws JsonSyntaxException, Exception {
		ArrayList<String> dates = new ArrayList<String>();
		
		Gson gson = new Gson();
		String response = readUrl(baseURL + "getTeacherDates");
		
		JsonElement element = new JsonParser().parse(response);
		JsonObject obj = element.getAsJsonObject();
		for(JsonElement date: obj.getAsJsonObject("data").getAsJsonArray("dates")){
			dates.add(date.getAsString());
		}
		
		return dates;
	}
	
	public static ArrayList<String> getStudentDates() throws JsonSyntaxException, Exception {
		ArrayList<String> dates = new ArrayList<String>();
		
		Gson gson = new Gson();
		String response = readUrl(baseURL + "getStudentDates");
		JsonElement element = new JsonParser().parse(response);
		JsonObject obj = element.getAsJsonObject();
		for(JsonElement date: obj.getAsJsonObject("data").getAsJsonArray("dates")){
			dates.add(date.getAsString());
		}
		
		return dates;
		
	}
	
	private static String getErrorMessage(String json){
		JsonElement element = new JsonParser().parse(json);
		JsonObject obj = element.getAsJsonObject();
		return obj.get("message").toString();
	}
	
	private static boolean isOk(String json){
		JsonElement element = new JsonParser().parse(json);
		JsonObject obj = element.getAsJsonObject();
		if(obj.get("code").getAsInt()== 0)
			return true;
		return false;
	}
	
	private static String getData(String json){
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

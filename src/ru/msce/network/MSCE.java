package ru.msce.network;

import java.util.ArrayList;

import com.google.gson.JsonSyntaxException;

import ru.msce.model.Day;
import ru.msce.model.Group;

public class MSCE {

	private static String baseURL = "http://s1.al3xable.me/method/";

	public static Day getStudent() throws JsonSyntaxException, Exception {
		return Networking.getDayByURL(baseURL + "getStudent");
	}

	public static Day getStudent(String date) throws JsonSyntaxException, Exception {
		return Networking.getDayByURL(baseURL + "getStudent?date=" + date);
	}

	public static Group getGroup(String group) throws JsonSyntaxException, Exception {
		return Networking.getGroupByURL(baseURL + "getStudent?group=" + group);
	}

	public static Group getTeacher(String group, String date) throws JsonSyntaxException, Exception {
		return Networking.getGroupByURL(baseURL + "getTeacher?teacher=" + group + "&date = " + date);
	}

	public static Group getGroup(String group, String date) throws JsonSyntaxException, Exception {
		return Networking.getGroupByURL(baseURL + "getStudent?group=" + group + "&date = " + date);
	}

	public static ArrayList<String> getTeacherDates() throws JsonSyntaxException, Exception {
		return Networking.getDatesByURL(baseURL + "getTeacherDates");
	}

	public static ArrayList<String> getStudentDates() throws JsonSyntaxException, Exception {
		return Networking.getDatesByURL(baseURL + "getStudentDates");
	}
}

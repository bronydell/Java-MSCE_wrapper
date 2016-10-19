package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day {
	private String date;
	private ArrayList<Group> groups;

	/**
	 * Getter for date
	 * 
	 * @return Date of recording
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Setter for date
	 * 
	 * @param Date
	 *            of recording
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Getter for groups
	 * 
	 * @return Group array
	 */
	public ArrayList<Group> getGroups() {
		return groups;
	}

	/**
	 * Setter for groups
	 * 
	 * @param Group
	 *            array
	 */
	public void setGroups(ArrayList<Group> groups) {
		this.groups = groups;
	}
}

package com.skd;

import dev.langchain4j.agent.tool.Tool;

public class CourseTools {

	@Tool("Get the price of the course")
	public String getCoursePrice(String course) {
		System.out.println(course);
		switch (course) {
		case "Java":
			return "300 INR";
		case "Python":
			return "250 INR";
		}

		return "Course Price NA";

	}

	@Tool("Get the duration of the course")
	public String getCourseDuration(String course) {
		System.out.println(course);
		switch (course) {
		case "Java":
			return "3 Months";
		case "Python":
			return "2 Months";
		}

		return "Course Price NA";
	}
}

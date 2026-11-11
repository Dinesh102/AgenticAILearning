package com.skd.tools;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;

public class DateTimeTool {

	@Tool("Get the current date time for timezone that is requested")
	public String getCurrentDatetime(@P String timeZone) {

		System.out.println(timeZone);

		ZoneId zoneId = ZoneId.of(timeZone);

		ZonedDateTime currentDateTime = ZonedDateTime.now(zoneId);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		return currentDateTime.format(formatter);
	}
}

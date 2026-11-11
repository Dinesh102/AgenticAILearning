package com.skd.tools;

import java.time.LocalDate;
import java.time.LocalDateTime;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;

public class DateTimeTool {

	@Tool("Get the current date time")
	public LocalDateTime getCurrentDatetime(@P String timeZone) {
		return LocalDateTime.now();
	}
}

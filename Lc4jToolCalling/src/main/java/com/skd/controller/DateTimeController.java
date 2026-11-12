package com.skd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skd.aiservice.DateTimeAsisstance;

@RestController
@RequestMapping("/api")
public class DateTimeController {
	private final DateTimeAsisstance assistant;

	public DateTimeController(DateTimeAsisstance assistant) {
		this.assistant = assistant;
	}

	@GetMapping("/time")
	public String getTime(@RequestParam String question) {
		return assistant.chat(question);
	}

}

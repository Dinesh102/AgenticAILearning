package com.skd.springboot_sdk_lc4j.controller;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface JavaAssitant {

	String chat(String message);

	@SystemMessage("Act as java expert and answer")
	String explain(String message);
}

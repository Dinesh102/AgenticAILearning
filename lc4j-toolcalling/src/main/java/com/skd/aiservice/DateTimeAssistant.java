package com.skd.aiservice;

import dev.langchain4j.service.SystemMessage;

public interface DateTimeAssistant {

	@SystemMessage("You are helpful date time assistance")
	String chat(String message);

}

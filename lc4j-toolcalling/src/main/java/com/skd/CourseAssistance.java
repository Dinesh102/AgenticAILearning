package com.skd;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface CourseAssistance {

	@SystemMessage("You are helpful assiatnce, use tolls to answer course price and duration and dont invent it")
	@UserMessage("{{it}}")
	String chat(String message);
}

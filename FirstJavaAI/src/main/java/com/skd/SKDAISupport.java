package com.skd;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface SKDAISupport {

	@SystemMessage("Repond very professionally and politely anything asked outside java deny it")
	@UserMessage("Explain {{topic}} in two lines")
	String response(@V("topic") String request);
}

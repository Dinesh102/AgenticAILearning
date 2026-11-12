package com.skd.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.skd.aiservice.DateTimeAsisstance;
import com.skd.tools.DateTimeTool;

import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

@Configuration
public class AIConfig {

	@Value("${openai.key}")
	private String apiKey;

	@Value("${openai.model}")
	private String modelName;

	@Bean
	OpenAiChatModel langChain4JClient() {
		return OpenAiChatModel.builder().apiKey(apiKey).modelName("gpt-5.4-mini").build();
	}

	@Bean
	DateTimeTool dateTimeTool() {
		return new DateTimeTool();
	}

	@Bean
	public DateTimeAsisstance dateTimeAssistant(OpenAiChatModel model, DateTimeTool tools) {

		return AiServices.builder(DateTimeAsisstance.class).chatModel(model).tools(tools).build();
	}

}


package com.skd.springboot_sdk_lc4j.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;

import dev.langchain4j.model.openai.OpenAiChatModel;

@Configuration
public class AIConfig {

	@Value("${openai.key}")
	private String apiKey;

	@Value("${openai.model}")
	private String modelName;

	@Bean
	public OpenAIClient openAIClient() {
		return OpenAIOkHttpClient.builder().apiKey(apiKey).build();
	}

	@Bean
	public OpenAiChatModel langChain4JClient() {
		return OpenAiChatModel.builder().apiKey(apiKey).modelName("gpt-5.4-mini").build();
	}

}

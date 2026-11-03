
package com.skd.springboot_sdk_lc4j.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;

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
}

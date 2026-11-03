package com.skd.springboot_sdk_lc4j.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openai.client.OpenAIClient;
import com.openai.models.ChatModel;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;

@RestController
@RequestMapping("/api/openai")
public class OpenAIController {

	private final OpenAIClient client;

	public OpenAIController(OpenAIClient client) {
		this.client = client;
	}

	@GetMapping("/chat")
	public String chat(@RequestParam String message) {
		ChatCompletionCreateParams param = ChatCompletionCreateParams.builder().model(ChatModel.GPT_5_4_MINI)
				.addUserMessage(message).build();

		return client.chat().completions().create(param).choices().get(0).message().content().orElse("Failed");

	}

	@GetMapping("/chat/metdata")
	public ChatCompletion chatMD(@RequestParam String message) {
		ChatCompletionCreateParams param = ChatCompletionCreateParams.builder().model(ChatModel.GPT_5_4_MINI)
				.addUserMessage(message).build();

		return client.chat().completions().create(param);

	}

}

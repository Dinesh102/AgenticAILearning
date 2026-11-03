package com.skd.controller;

import java.time.Duration;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.OpenAiChatOptions.Builder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checks")
public class TimeOutConnErrorRetiresLimitsSpringAI {

	private final OpenAiChatModel chatModel;

	private final ChatClient chatClient;

	public TimeOutConnErrorRetiresLimitsSpringAI(OpenAiChatModel chatModel) {
		this.chatModel = chatModel;
		this.chatClient = ChatClient.create(chatModel);
	}

	@GetMapping("/ask")
	public String askModel(@RequestParam String message) {
		return chatModel.call(message);
	}

	@GetMapping("/chat")
	public String chatModel(@RequestParam String message) {
		try {

			Builder builder = OpenAiChatOptions.builder().timeout(Duration.ofSeconds(10)).maxRetries(5);
			return chatClient.prompt().options(builder).system("Act as Java developer and deny any other request")
					.user(message).call().content();
		} catch (Exception e) {

			/**
			 * com.openai.errors.UnauthorizedException - 401 - Model Name wrong
			 * 
			 * com.openai.errors.NotFoundException - 404: The model `gpt-5.4-mini1` does not
			 * exist or you do not have access to it.
			 * 
			 * OpenAIIoException - timeout
			 */
			return e.getMessage() + e.getClass();
		}
	}

	@GetMapping("/ChatResponse")
	public ChatResponse ChatResponse(@RequestParam String message) {
		try {

			Builder builder = OpenAiChatOptions.builder().timeout(Duration.ofSeconds(1)).maxRetries(5);
			return chatClient.prompt().options(builder).system("Act as Java developer and deny any other request")
					.user(message).call().chatResponse();
		} catch (Exception e) {

			/**
			 * com.openai.errors.UnauthorizedException - 401 - Model Name wrong
			 * 
			 * com.openai.errors.NotFoundException - 404: The model `gpt-5.4-mini1` does not
			 * exist or you do not have access to it.
			 * 
			 * OpenAIIoException - timeout
			 */
			System.out.println(e.getMessage());
		}
		return null;
	}
}

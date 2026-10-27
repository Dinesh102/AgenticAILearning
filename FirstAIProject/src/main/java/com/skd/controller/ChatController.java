package com.skd.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {

	private final OpenAiChatModel chatModel;

	private final ChatClient chatClient;

	public ChatController(OpenAiChatModel chatModel) {
		this.chatModel = chatModel;
		this.chatClient = ChatClient.create(chatModel);
	}

	@GetMapping("/ask")
	public String askModel(@RequestParam String message) {
		return chatModel.call(message);
	}

	@GetMapping("/chat")
	public String chatModel(@RequestParam String message) {
		return chatClient.prompt().system("Act as Java developer and deny any other request").user(message).call()
				.content();
	}
}

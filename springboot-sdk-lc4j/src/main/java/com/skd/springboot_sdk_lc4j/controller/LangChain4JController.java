package com.skd.springboot_sdk_lc4j.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.langchain4j.model.chat.ChatModel;

@RestController
@RequestMapping("/api/lc4j")
public class LangChain4JController {

	private final JavaAssitant assist;

	private final ChatModel chatModel;

	public LangChain4JController(ChatModel chatModel, JavaAssitant assist) {
		this.chatModel = chatModel;
		this.assist = assist;
	}

	@GetMapping("/chat")
	public String chat(@RequestParam String message) {
		return chatModel.chat(message);
	}

	@GetMapping("/chatai")
	public String chatAiService(@RequestParam String message) {
		return assist.chat(message);
	}

}

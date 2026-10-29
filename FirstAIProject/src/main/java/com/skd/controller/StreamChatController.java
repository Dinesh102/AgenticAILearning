package com.skd.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/apistream")
public class StreamChatController {

	private final OpenAiChatModel chatModel;

	private final ChatClient chatClient;

	public StreamChatController(OpenAiChatModel chatModel) {
		this.chatModel = chatModel;
		this.chatClient = ChatClient.create(chatModel);
	}

	@GetMapping("/ask")
	public Flux<String> askModel(@RequestParam String message) {
		return chatModel.stream(message);

	}

	@GetMapping("/chat")
	public Flux<String> chatModel(@RequestParam String message) {
		return chatClient.prompt().system("Act as Java developer and deny any other request").user(message).stream()
				.content();
	}

	@GetMapping("/chat/md")
	public Flux<String> chatModelWithMetaData(@RequestParam String message) {
		return chatClient.prompt().system("Act as Java developer and deny any other request").user(message).stream()
				.chatResponse().map(res -> {
					return new StringBuilder().append((res.getResult() != null && res.getResult().getMetadata() != null)
							? res.getResult().getMetadata()
							: "Empty Metadata")
							.append((res.getResult() != null && res.getResult().getMetadata() != null)
									? res.getResult().getOutput().getText()
									: " EMpty response").toString();
				});
	}
}

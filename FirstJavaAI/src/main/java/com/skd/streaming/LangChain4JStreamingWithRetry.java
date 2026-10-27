package com.skd.streaming;

import java.util.concurrent.CompletableFuture;

import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;

public class LangChain4JStreamingWithRetry {

	public static void main(String[] args) {

//		SystemMessage sysMes = SystemMessage
//				.systemMessage("Repond very professionally and politely anything asked outside java deny it");

		String apiKey = System.getenv("OPENAI_API_KEY");

//		OpenAiChatModel model = OpenAiChatModel.builder().apiKey(apiKey).modelName("gpt-5.4-mini").build();
//
//		UserMessage userMess = UserMessage
//				.userMessage("Say something nice and add 'Hello to java World' and add very importatnt concent");
//		ChatResponse answer = model.chat(sysMes, userMess);
//		System.out.println(answer.aiMessage().text());

		OpenAiStreamingChatModel streamModel = OpenAiStreamingChatModel.builder().apiKey(apiKey)
				.modelName("gpt-5.4-mini").build();

		CompletableFuture<ChatResponse> chatStreamRes = new CompletableFuture<>();

		streamModel.chat("Explain very importatnt concent", new StreamingChatResponseHandler() {

			@Override
			public void onPartialResponse(String partialMessage) {
				System.out.println("partital" + partialMessage);

			}

			@Override
			public void onCompleteResponse(ChatResponse completeResponse) {
				chatStreamRes.complete(completeResponse);
				System.out.println("partital" + completeResponse.aiMessage().text());
			}

			@Override
			public void onError(Throwable error) {
				chatStreamRes.completeExceptionally(error);
			}

		});

		chatStreamRes.join();
	}

}

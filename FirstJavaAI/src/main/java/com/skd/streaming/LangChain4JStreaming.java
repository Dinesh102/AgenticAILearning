package com.skd.streaming;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;

public class LangChain4JStreaming {

	public static void main(String[] args) {

		String apiKey = System.getenv("OPENAI_API_KEY");

		OpenAiStreamingChatModel streamModel = OpenAiStreamingChatModel.builder().apiKey(apiKey)
				.modelName("gpt-5.4-mini").build();

		CompletableFuture<ChatResponse> chatStreamRes = new CompletableFuture<>();

		StringBuilder initalResponse = new StringBuilder();

		streamModel.chat("Explain very importatnt concent in 100 words", new StreamingChatResponseHandler() {

			@Override
			public void onPartialResponse(String partialMessage) {
				initalResponse.append(partialMessage);
				if (partialMessage.toString().length() > 50) {
					chatStreamRes.complete(false);
				}
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

		CompletableFuture<ChatResponse> chatStreamRetryRes = new CompletableFuture<>();

		streamModel.chat(
				List.of("Explain very importatnt concent in 100 words", initalResponse.toString(),
				"dont repeat contine from where it was left"), new StreamingChatResponseHandler() {

					@Override
					public void onPartialResponse(String partialMessage) {

					}

					@Override
					public void onCompleteResponse(ChatResponse completeResponse) {
						chatStreamRetryRes.complete(completeResponse);
						System.out.println("partital" + completeResponse.aiMessage().text());

					}

					@Override
					public void onError(Throwable error) {
						chatStreamRetryRes.completeExceptionally(error);
					}

				});
	}

}

package com.skd.streaming;

import java.util.concurrent.CompletableFuture;

import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.PartialResponse;
import dev.langchain4j.model.chat.response.PartialResponseContext;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;

public class LangChain4JStreamingWithRetry {

	public static void main(String[] args) {

		String apiKey = System.getenv("OPENAI_API_KEY");

		OpenAiStreamingChatModel streamModel = OpenAiStreamingChatModel.builder().apiKey(apiKey)
				.modelName("gpt-5.4-mini").build();

		CompletableFuture<Boolean> chatStreamRes = new CompletableFuture<>();

		StringBuilder initalResponse = new StringBuilder();

		streamModel.chat("Explain very importatnt concent in java 100 words", new StreamingChatResponseHandler() {

			@Override
			public void onPartialResponse(PartialResponse partialMessage, PartialResponseContext context) {

				if (initalResponse.toString().length() > 100) {
					chatStreamRes.complete(false);

				} else {
					initalResponse.append(partialMessage.text());
				}

			}

			@Override
			public void onCompleteResponse(ChatResponse completeResponse) {
				chatStreamRes.complete(true);
				System.out.println("11" + completeResponse.aiMessage().text());

			}

			@Override
			public void onError(Throwable error) {
				chatStreamRes.complete(false);
			}

		});

		Boolean status = chatStreamRes.join();

		if (status) {
			return;
		}

		System.out.println("1" + initalResponse.toString());

		CompletableFuture<ChatResponse> chatStreamRetryRes = new CompletableFuture<>();

		streamModel.chat(
				new StringBuilder().append("Explain very importatnt concent in java 100 words. faied response : ")
						.append(initalResponse.toString())
						.append(";dont repeat contine from where it failed attaching the failed response ").toString(),
				new StreamingChatResponseHandler() {

					@Override
					public void onPartialResponse(String partialMessage) {

					}

					@Override
					public void onCompleteResponse(ChatResponse completeResponse) {
						chatStreamRetryRes.complete(completeResponse);
						System.out.println("22" + completeResponse.aiMessage().text());

					}

					@Override
					public void onError(Throwable error) {
						chatStreamRetryRes.completeExceptionally(error);
					}

				});

		chatStreamRetryRes.join();
	}

}

package com.skd.basic;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.core.http.StreamResponse;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import com.openai.models.responses.ResponseStreamEvent;
import com.openai.models.responses.ResponseUsage;

public class SDKJavaAI {

	public static void main(String[] args) {
//	    OpenAIClient client = OpenAIOkHttpClient.fromEnv();

		String apiKey = System.getenv("OPENAI_API_KEY");
		OpenAIClient client = OpenAIOkHttpClient.builder().apiKey(apiKey).build();

		ResponseCreateParams params = ResponseCreateParams.builder().model("gpt-5.4-mini").input(
				"open ai lib used to generate the code sucessfull execute using java dependecy bit how did it ran")
				.build();

		try (StreamResponse<ResponseStreamEvent> response = client.responses().createStreaming(params)) {

			response.stream().forEach(event -> {
				event.outputTextDelta().ifPresent(textEvent -> System.out.print(textEvent.delta()));

				// the complete response with token usage
				event.completed().ifPresent(completedEvent -> {
					// Completed event contains final response metadata, including usage if
					// available.
					ResponseUsage usage = completedEvent.response().usage().orElseThrow();
					System.out.println("\n");
					System.out.println("input tokens  : " + usage.inputTokens());
					System.out.println("output tokens : " + usage.outputTokens());
				});
			});
		}

	}
}

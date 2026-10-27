package com.skd.basic;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.core.http.StreamResponse;
import com.openai.models.chat.completions.ChatCompletionChunk;
import com.openai.models.chat.completions.ChatCompletionCreateParams;

public class JavaIAI {

	public static void main(String[] args) {
		String apiKey = System.getenv("OPENAI_API_KEY");

		OpenAIClient client = OpenAIOkHttpClient.builder().apiKey(apiKey).build();

		String systemMessage = """
					Act as Java  Expert.
					Anything asked on programming language just explain the concept

					Dont exceed reponse more than lines
				""";

		String userMessage = """
					what is blocking vs streaming
				""";

		ChatCompletionCreateParams params = ChatCompletionCreateParams.builder().model("gpt-5.4-mini")
				.addSystemMessage(systemMessage).addUserMessage(userMessage).temperature(0).build();
		String response = client.chat().completions().create(params).choices().get(0).message().content().orElse("");

		System.out.println(response);

		System.out.println("====================================");

	}

}

package com.skd;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.openai.models.chat.completions.ChatCompletionMessage;

public class SDKJavaAIWithTemp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String apiKey = System.getenv("OPENAI_API_KEY");

		OpenAIClient client = OpenAIOkHttpClient.builder().apiKey(apiKey).build();

		String systemMessage = """
					Act as Java  Expert.
					Anything asked on programming language just explain the concept

					Dont exceed reponse more than 2lines
				""";

		String userMessage = """
					create me a nice song for java developer in one sentence
				""";

		for (int i = 0; i < 5; i++) {
			ChatCompletionCreateParams firstConversion = ChatCompletionCreateParams.builder().model("gpt-5.4-mini")
					.addSystemMessage(systemMessage).addUserMessage(userMessage).temperature(0).build();
			String response = ask(client, firstConversion);

			System.out.println(response);

			System.out.println("====================================");

		}

		for (int i = 0; i < 5; i++) {
			ChatCompletionCreateParams firstConversion = ChatCompletionCreateParams.builder().model("gpt-5.4-mini")
					.addSystemMessage(systemMessage).addUserMessage(userMessage).temperature(1).build();
			String response = ask(client, firstConversion);

			System.out.println(response);

			System.out.println("====================================");

		}

	}

	private static String ask(OpenAIClient client, ChatCompletionCreateParams params) {
		return client.chat().completions().create(params).choices().get(0).message().content().orElse("");
	}

}

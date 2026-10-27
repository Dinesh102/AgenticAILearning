package com.skd;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.openai.models.chat.completions.ChatCompletionMessage;

public class SDKJavaAIWithConvHist {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String apiKey = System.getenv("OPENAI_API_KEY");

		OpenAIClient client = OpenAIOkHttpClient.builder().apiKey(apiKey).build();

		String systemMessage = """
					Act as Java  Expert.
					Anything asked on programming language just explain the concept

					Dont exceed reponse more than 2lines
				""";

		String userMessage1 = """
					My userid is PythonDeveloper001
				""";

		ChatCompletionCreateParams firstConversion = ChatCompletionCreateParams.builder().model("gpt-5.4-mini")
				.addSystemMessage(systemMessage).addUserMessage(userMessage1).build();
		String response1 = ask(client, firstConversion);

		System.out.println(response1);

		System.out.println("====================================");

		String userMessage2 = """
					and What is singleton in python
				""";

		ChatCompletionCreateParams secondConversion = ChatCompletionCreateParams.builder().model("gpt-5.4-mini")
				.addSystemMessage(systemMessage).addAssistantMessage(response1).addUserMessage(userMessage2).build();
		String response2 = ask(client, secondConversion);

		System.out.println(response2);

		System.out.println("====================================");

		String userMessage3 = """
					what is my userid and what is previous question
				""";

		ChatCompletionCreateParams thirdConversion = ChatCompletionCreateParams.builder().model("gpt-5.4-mini")
				.addSystemMessage(systemMessage).addAssistantMessage(response1).addAssistantMessage(response2)
				.addUserMessage(userMessage3).build();
		String response3 = ask(client, thirdConversion);

		System.out.println(response3);

	}

	private static String ask(OpenAIClient client, ChatCompletionCreateParams params) {
		return client.chat().completions().create(params).choices().get(0).message().content().orElse("");
	}

}

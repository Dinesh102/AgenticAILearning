package com.skd;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;

public class SDKJavaAI {

	public static void main(String[] args) {
//	    OpenAIClient client = OpenAIOkHttpClient.fromEnv();

		String apiKey = System.getenv("OPENAI_API_KEY");
		OpenAIClient client = OpenAIOkHttpClient.builder().apiKey(apiKey).build();

		ResponseCreateParams params = ResponseCreateParams.builder().model("gpt-5.4-mini").input(
				"open ai lib used to generate the code sucessfull execute using java dependecy bit how did it ran")
				.build();

		Response response = client.responses().create(params);

		response.output().stream().flatMap(item -> item.message().stream())
				.flatMap(message -> message.content().stream()).flatMap(content -> content.outputText().stream())
				.forEach(outputText -> System.out.println(outputText.text()));
	}

}

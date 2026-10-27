package com.skd;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

public class LangChain4JAIServices {

	public static void main(String[] args) {

		ChatModel model = OpenAiChatModel.builder().apiKey(System.getenv("OPENAI_API_KEY")).modelName("gpt-5.4-mini").build();

		SKDAISupport support = AiServices.create(SKDAISupport.class, model);
		String answer = support.response("Dependency Injection");

		System.out.println(answer);

		answer = support.response("python");

		System.out.println(answer);

		answer = support.response("Global economy");

		System.out.println(answer);
	}
}

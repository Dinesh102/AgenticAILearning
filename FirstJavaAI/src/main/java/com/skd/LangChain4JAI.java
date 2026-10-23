package com.skd;

import dev.langchain4j.model.openai.OpenAiChatModel;

public class LangChain4JAI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		OpenAiChatModel model = OpenAiChatModel.builder().apiKey(apiKey).modelName("gpt-4o-mini").build();

		String answer = model.chat("Say something nice and add 'Hello to java World'");
		System.out.println(answer); // Hello World
	}

}

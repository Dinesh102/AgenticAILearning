package com.skd.basic;

import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;

public class LangChain4JAI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SystemMessage sysMes = SystemMessage
				.systemMessage("Repond very professionally and politely anything asked outside java deny it");

		String apiKey = System.getenv("OPENAI_API_KEY");

		OpenAiChatModel model = OpenAiChatModel.builder().apiKey(apiKey).modelName("gpt-5.4-mini").build();

		UserMessage userMess = UserMessage.userMessage("Say something nice and add 'Hello to java World'");
		ChatResponse answer = model.chat(sysMes, userMess);
		System.out.println(answer.aiMessage().text());

	}

}

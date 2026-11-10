package com.skd;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

public class LangChain4JAIServicesToolCalling {

	public static void main(String[] args) {

		ChatModel model = OpenAiChatModel.builder().apiKey(System.getenv("OPENAI_API_KEY")).modelName("gpt-5.4-mini")
				.build();

		CourseTools tools = new CourseTools();

		CourseAssistance support = AiServices.builder(CourseAssistance.class).chatModel(model).tools(tools).build();
		String answer = support.chat("what is python couse price and how many days");

		System.out.println(answer);

	}
}

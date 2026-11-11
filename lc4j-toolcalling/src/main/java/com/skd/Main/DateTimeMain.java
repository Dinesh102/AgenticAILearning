package com.skd.Main;

import com.skd.CourseAssistance;
import com.skd.CourseTools;
import com.skd.aiservice.DateTimeAssistant;
import com.skd.tools.DateTimeTool;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

public class DateTimeMain {

	public static void main(String[] args) {

		ChatModel model = OpenAiChatModel.builder().apiKey(System.getenv("OPENAI_API_KEY")).modelName("gpt-5.4-mini")
				.build();

		DateTimeTool tools = new DateTimeTool();

		DateTimeAssistant support = AiServices.builder(DateTimeAssistant.class).chatModel(model).tools(tools).build();
		String answer = support.chat("what is current time in India");

		System.out.println(answer);

	}

}

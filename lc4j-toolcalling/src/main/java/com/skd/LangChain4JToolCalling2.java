package com.skd;

import java.util.List;

import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.agent.tool.ToolSpecifications;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

public class LangChain4JToolCalling2 {

	public static void main(String[] args) {

		ChatModel model = OpenAiChatModel.builder().apiKey(System.getenv("OPENAI_API_KEY")).modelName("gpt-5.4-mini")
				.logRequests(true).logResponses(true).build();

		CourseTools tools = new CourseTools();

		List<ToolSpecification> toolsSpec = ToolSpecifications.toolSpecificationsFrom(tools);

		ChatRequest request = ChatRequest.builder()
				.messages(UserMessage.from("what is Python course price and how many days"))
				.toolSpecifications(toolsSpec).build();
		ChatResponse res = model.chat(request);

//		CourseAssistance support = AiServices.builder(CourseAssistance.class).chatModel(model).tools(tools).build();
//		String answer = support.chat("what is python couse price and how many days");

		System.out.println(request);

		System.out.println(res);

		if (res.aiMessage().hasToolExecutionRequests()) {
			String arg = res.aiMessage().toolExecutionRequests().getFirst().arguments();

			String response = model.chat(tools.getCourseDuration(arg));

			System.out.println(model.chat(response));

		}

	}
}

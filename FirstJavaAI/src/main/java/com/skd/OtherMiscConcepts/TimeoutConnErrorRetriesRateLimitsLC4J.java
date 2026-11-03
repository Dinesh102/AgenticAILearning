package com.skd.OtherMiscConcepts;

import java.time.Duration;

import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.exception.AuthenticationException;
import dev.langchain4j.exception.ModelNotFoundException;
import dev.langchain4j.exception.NonRetriableException;
import dev.langchain4j.exception.RetriableException;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;

public class TimeoutConnErrorRetriesRateLimitsLC4J {

	public static void main(String[] args) {

//		retires();

		retireError();

		
		
		
	}

	private static void retires() {
		String apiKey = System.getenv("OPENAI_API_KEY");

		OpenAiChatModel model = OpenAiChatModel.builder().apiKey(apiKey).modelName("gpt-5.4-mini")
				.timeout(Duration.ofMillis(500)).maxRetries(3).build();

		UserMessage userMess = UserMessage
				.userMessage("Say something nice and add 'Hello to java World' and explain in 100words");

		SystemMessage sysMes = SystemMessage
				.systemMessage("Repond very professionally and politely anything asked outside java deny it");

		try {
			ChatResponse answer = model.chat(sysMes, userMess);
			System.out.println(answer.aiMessage().text());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO: handle exception
		}

	}

	/*
	 * ** Retry able Exception
	 * 
	 * -> Time out exception -> Rate Limit Exception -> Internal server exception
	 * 
	 * 
	 * ** Non Retry able exception
	 * 
	 * -> Authentication Exception -> Invalid request exception -> Model not found
	 * exception
	 */
	private static void retireError() {
		String apiKey = System.getenv("OPENAI_API_KEY");

		OpenAiChatModel model = OpenAiChatModel.builder().apiKey(apiKey).modelName("gpt-5.4-mini")
				.timeout(Duration.ofMillis(500)).maxRetries(3).build();

		UserMessage userMess = UserMessage
				.userMessage("Say something nice and add 'Hello to java World' and explain in 100words");

		SystemMessage sysMes = SystemMessage
				.systemMessage("Repond very professionally and politely anything asked outside java deny it");

		try {
			ChatResponse answer = model.chat(sysMes, userMess);
			System.out.println(answer.aiMessage().text());
		} catch (AuthenticationException e) {
			System.out.println("NonRetriableException - AuthenticationException " + e.getMessage());
		} catch (ModelNotFoundException e) {
			System.out.println("NonRetriableException " + e.getMessage());
		} catch (RetriableException e) {
			System.out.println("RetriableException " + e.getMessage());
		} catch (NonRetriableException e) {
			System.out.println("NonRetriableException" + e.getMessage());
		}

	}

}

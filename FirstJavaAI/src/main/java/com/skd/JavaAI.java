package com.skd;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class JavaAI {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("Java AI");

		System.out.println(System.getProperty("java.version"));

		String apiKey = System.getenv("OPENAI_API_KEY");
		String body = """
				{
					"model" : "gpt-5.4-mini",
					"input" : "First AI API invokde succuessfully!"
				}
				""";
		HttpClient httpClient = HttpClient.newHttpClient();

		HttpRequest httpReq = HttpRequest.newBuilder().uri(URI.create("https://api.openai.com/v1/responses"))
				.header("Authorization", "Bearer " + apiKey).POST(BodyPublishers.ofString(body)).build();

		HttpResponse<String> response = httpClient.send(httpReq, BodyHandlers.ofString());

		System.out.println(response.body());

		// HttpClient httpClient = HttpClient.newHttpClient();
//		
//	
//
//		HttpRequest req = HttpRequest.newBuilder()
//				.uri(URI.create("https://api.openai.com/v1/chat/completions"))
//				.header("Authorization", "Bearer " + apiKey)
//				.header("Content-Type", "application/json")
//				.POST(HttpRequest.BodyPublishers.ofString("{ \"model\": \"gpt-5.4-mini\",\"Prompt\": \"Who are you\" , \"max_tokens\":5}")).build();
//
//		HttpResponse<String> response = httpClient.send(req, HttpResponse.BodyHandlers.ofString());
//
//		System.out.println(response.body());
	}

}

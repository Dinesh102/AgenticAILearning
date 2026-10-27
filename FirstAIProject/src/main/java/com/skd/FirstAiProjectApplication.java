package com.skd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.skd"})
public class FirstAiProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstAiProjectApplication.class, args);
	}

}

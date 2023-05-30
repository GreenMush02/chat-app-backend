package com.novoakademia.chatappbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.novoakademia"})
@SpringBootApplication
public class ChatAppBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatAppBackendApplication.class, args);
	}

}

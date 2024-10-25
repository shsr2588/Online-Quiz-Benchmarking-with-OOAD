package com.ooad.online_quiz_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OnlineQuizSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineQuizSystemApplication.class, args);
	}

}

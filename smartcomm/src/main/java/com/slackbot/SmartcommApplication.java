package com.slackbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"me.ramswaroop.jbot", "com"})
public class SmartcommApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartcommApplication.class, args);
	}
}


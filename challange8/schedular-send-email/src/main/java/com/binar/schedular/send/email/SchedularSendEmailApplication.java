package com.binar.schedular.send.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class SchedularSendEmailApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchedularSendEmailApplication.class, args);
	}

}

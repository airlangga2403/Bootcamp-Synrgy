package com.binarfood.discovery.binar.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class BinarConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BinarConfigServerApplication.class, args);
	}

}

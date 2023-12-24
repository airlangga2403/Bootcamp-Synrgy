package com.binarfood.discovery.binar.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BinarDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BinarDiscoveryApplication.class, args);
	}

}

package com.demo.microservice.client.getcustomerInfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GetcustomerInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetcustomerInfoApplication.class, args);
	}

}

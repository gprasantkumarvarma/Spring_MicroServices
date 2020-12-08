package com.mesaging.integration.MQProcessing;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MqProcessingApplication implements CommandLineRunner {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public static void main(String[] args) {
		
		SpringApplication.run(MqProcessingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	
		SimpleMessage message = new SimpleMessage();
			
		message.setName("First Message--Test2");
		message.setDiscription("Simple Desc");
		
		//rabbitTemplate.convertAndSend("TestExchange", "testRouting", "Hi This is my first RabbitMQ programme ");

		
		rabbitTemplate.convertAndSend("NewTopicExchange", "myfirstbinding", message);

	}

}

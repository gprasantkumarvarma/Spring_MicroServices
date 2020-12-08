package com.mesaging.integration.MQProcessing;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RebbitMQQueueConfiguration {

	@Bean
	Queue exampleQueue() {
		return new Queue("ExampleQueue", true);
	}
	
	@Bean
	Queue exampleQueue2() {
		return QueueBuilder.durable("ExampleQueue2")
				.autoDelete()
				.exclusive()
				.build();
	}
}

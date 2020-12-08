package com.mesaging.integration.MQProcessing;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMQConfig {

	private final String MY_QUEUE = "MyQueue";

	@Bean
	Queue myQueue() {
		return new Queue(MY_QUEUE, true);
	}

	@Bean
	Exchange myexchange() {
		return ExchangeBuilder.topicExchange("NewTopicExchange")
				.durable(true)
				.build();
	}
	
	@Bean
	Binding binding() {
		//return new Binding(MY_QUEUE, Binding.DestinationType.QUEUE, "NewTopicExchange", "topic", null);
		
		  return BindingBuilder .bind(myQueue()) .to(myexchange())
		  .with("myfirstbinding") .noargs();
		 
		 
				
	}
	
	@Bean
	ConnectionFactory connectionFactory() {
		CachingConnectionFactory factory = new CachingConnectionFactory("localhost");
		factory.setUsername("guest");
		factory.setPassword("guest");
		return factory;
	}
	
	@Bean
	MessageListenerContainer  messagelistenercontainer() {
		SimpleMessageListenerContainer simplemessagelistenercontainer = new SimpleMessageListenerContainer();
		simplemessagelistenercontainer.setConnectionFactory(connectionFactory());
		simplemessagelistenercontainer.setQueues(myQueue());
		simplemessagelistenercontainer.setMessageListener(new RabbitMQMessageListener());
		return simplemessagelistenercontainer;
	}
}

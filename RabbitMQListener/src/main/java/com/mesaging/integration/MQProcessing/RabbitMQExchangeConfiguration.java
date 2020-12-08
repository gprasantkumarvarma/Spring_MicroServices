package com.mesaging.integration.MQProcessing;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class RabbitMQExchangeConfiguration {
	
	@Bean
	Exchange exampleExchange() {
		return new TopicExchange("TopicExchangeExample1");
	}
	
	@Bean
	Exchange topicExchangeExample() {
		return ExchangeBuilder.topicExchange("TopicExchangeExample2")
				.autoDelete()
				.internal()
				.build();
	}
	
	
	@Bean
	Exchange directExchangeExample() {
		return ExchangeBuilder.directExchange("DirectExchangeExample")
				.autoDelete()
				.internal()
				.build();
	}
	
	@Bean
	Exchange hanoutExchangeExample() {
		return ExchangeBuilder.fanoutExchange("FanoutExchangeExample")
				.autoDelete()
				.internal()
				.build();
	}
	
	@Bean
	Exchange headersExchangeExample() {
		return ExchangeBuilder.headersExchange("HeadersExchangeExample")
				.autoDelete()
				.internal()
				.build();
	}

}

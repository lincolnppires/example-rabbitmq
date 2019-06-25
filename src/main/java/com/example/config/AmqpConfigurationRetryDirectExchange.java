package com.example.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfigurationRetryDirectExchange {
	
	@Configuration
	static class MovieConfigurationRetryDirectExchange{
		
		@Value("${conf-queue-exchange-direct-retry.exchange-movies-work}")
		private String moviesExchangeWork;
		@Value("${conf-queue-exchange-direct-retry.exchange-movies-wait}")
		private String moviesExchangeWait;
		@Value("${conf-queue-exchange-direct-retry.exchange-movies-dead}")
		private String moviesExchangeDead;
			
		@Bean
        public Exchange moviesExchangeWork() {
			return ExchangeBuilder.directExchange(moviesExchangeWork).durable(true).build();
        }
		
		@Bean
        public Exchange moviesExchangeWait() {
			return ExchangeBuilder.directExchange(moviesExchangeWait).durable(true).build();
        }
		
		@Bean
        public Exchange moviesExchangeDead() {
			return ExchangeBuilder.directExchange(moviesExchangeDead).durable(true).build();
        }

	}
	
	


}

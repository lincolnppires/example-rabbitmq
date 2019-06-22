package com.example.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfiguration {
	
	@Configuration
	static class MovieConfiguration{
		
		@Value("${conf-queue-exchange.exchange-movies}")
		private String moviesExchange;
		@Value("${conf-queue-exchange.queue-movie-drama}")
		private String movieDramaQueue;
		
		@Bean
		public Queue movieQueue() {
			return QueueBuilder.durable(movieDramaQueue).build();
		}
		
		@Bean
        public Exchange moviesExchange() {
            return ExchangeBuilder.fanoutExchange(moviesExchange).durable(true).build();
        }

		@Bean
        public Binding purchaseBindingUpdate() {
            return BindingBuilder
                .bind(movieQueue())
                .to(moviesExchange())
                .with("")
                .noargs();        
		}

	}

}

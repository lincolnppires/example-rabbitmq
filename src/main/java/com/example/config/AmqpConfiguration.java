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
public class AmqpConfiguration {
	
	@Configuration
	static class MovieConfiguration{
		
		@Value("${conf-queue-exchange.exchange-movies}")
		private String moviesExchange;
		@Value("${conf-queue-exchange.queue-movie-drama}")
		private String movieDramaQueue;		
		@Value("${conf-queue-exchange.queue-movie-thriller}")
		private String movieThrillerQueue;
		@Value("${conf-queue-exchange.queue-movie-all}")
		private String movieAllQueue;
		
		
		@Value("${conf-queue-exchange.exchange-movies-dlx}")
		private String moviesExchangeDlx;		
		@Value("${conf-queue-exchange.queue-movie-dlx}")
		private String movieDlxQueue;
		
		@Bean
		public Queue movieQueueDrama() {
			return QueueBuilder.durable(movieDramaQueue)
					.withArgument("x-dead-letter-exchange", moviesExchangeDlx)
					.build();
		}
		
		@Bean
		public Queue movieQueueThriller() {
			return QueueBuilder.durable(movieThrillerQueue)
					.withArgument("x-dead-letter-exchange", moviesExchangeDlx)
					.build();
		}
		
		@Bean
		public Queue movieQueueAll() {
			return QueueBuilder.durable(movieAllQueue)
					.withArgument("x-dead-letter-exchange", moviesExchangeDlx)
					.build();
		}
		
		@Bean
		public Queue movieQueueDlx() {
			return QueueBuilder.durable(movieDlxQueue).build();
		}
		
		@Bean
        public Exchange moviesExchange() {
			return ExchangeBuilder.topicExchange(moviesExchange).durable(true).build();
        }
		
		@Bean
        public Exchange moviesDlxExchange() {
			return ExchangeBuilder.fanoutExchange(moviesExchangeDlx).durable(true).build();
        }

		@Bean
        public Binding movieDramaBinding() {
            return BindingBuilder
                .bind(movieQueueDrama())
                .to(moviesExchange())
                .with("movie.drama")
                .noargs();        
		}
		
		@Bean
        public Binding movieThrillerBinding() {
            return BindingBuilder
                .bind(movieQueueThriller())
                .to(moviesExchange())
                .with("movie.thriller")
                .noargs();        
		}
		
		@Bean
        public Binding movieAllBinding() {
            return BindingBuilder
                .bind(movieQueueAll())
                .to(moviesExchange())
                .with("movie.#")
                .noargs();        
		}
		
		@Bean
        public Binding movieDlxBinding() {
            return BindingBuilder
                .bind(movieQueueDlx())
                .to(moviesDlxExchange())
                .with("")
                .noargs();        
		}

	}
	
	@Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


}

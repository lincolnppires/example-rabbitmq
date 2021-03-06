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
		
		@Value("${conf-queue-exchange-direct-retry.queue-movie-drama-work}")
		private String movieDramaWorkQueue;
		@Value("${conf-queue-exchange-direct-retry.queue-movie-drama-wait}")
		private String movieDramaWaitQueue;
		@Value("${conf-queue-exchange-direct-retry.queue-movie-drama-dead}")
		private String movieDramaDeadQueue;
		
		@Value("${conf-queue-exchange-direct-retry.queue-movie-thriller-work}")
		private String movieThrillerWorkQueue;
		@Value("${conf-queue-exchange-direct-retry.queue-movie-thriller-wait}")
		private String movieThrillerWaitQueue;
		@Value("${conf-queue-exchange-direct-retry.queue-movie-thriller-dead}")
		private String movieThrillerDeadQueue;
			
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

		@Bean
		public Queue movieDramaWork() {
			return QueueBuilder.durable(movieDramaWorkQueue)
					.withArgument("x-dead-letter-exchange", moviesExchangeWait)
					.build();
		}
		
		@Bean
		public Queue movieDramaWait() {
			return QueueBuilder.durable(movieDramaWaitQueue)
					.withArgument("x-message-ttl", 3000)
					.withArgument("x-dead-letter-exchange", moviesExchangeWork)
					.build();
			
		}
		
		@Bean
		public Queue movieDramaDead() {
			return QueueBuilder.durable(movieDramaDeadQueue)
					.build();
		}
		
		@Bean
		public Queue movieThrillerWork() {
			return QueueBuilder.durable(movieThrillerWorkQueue)
					.withArgument("x-dead-letter-exchange", moviesExchangeWait)
					.build();
		}
		
		@Bean
		public Queue movieThrillerWait() {
			return QueueBuilder.durable(movieThrillerWaitQueue)
					.withArgument("x-message-ttl", 3000)
					.withArgument("x-dead-letter-exchange", moviesExchangeWork)					
					.build();
		}
		
		@Bean
		public Queue movieThrillerDead() {
			return QueueBuilder.durable(movieThrillerDeadQueue)
					.build();
		}
		
		@Bean
        public Binding movieDramaWorkBinding() {
            return BindingBuilder
                .bind(movieDramaWork())
                .to(moviesExchangeWork())
                .with("movie.drama.work")
                .noargs();        
		}
		
		@Bean
        public Binding movieDramaWaitBinding() {
            return BindingBuilder
                .bind(movieDramaWait())
                .to(moviesExchangeWait())
                .with("movie.drama.wait")
                .noargs();        
		}
		
		@Bean
        public Binding movieDramaDeadBinding() {
            return BindingBuilder
                .bind(movieDramaDead())
                .to(moviesExchangeDead())
                .with("movie.drama.dead")
                .noargs();        
		}
		
		@Bean
        public Binding movieThrillerWorkBinding() {
            return BindingBuilder
                .bind(movieThrillerWork())
                .to(moviesExchangeWork())
                .with("movie.thriller.work")
                .noargs();        
		}
		
		@Bean
        public Binding movieThrillerWaitBinding() {
            return BindingBuilder
                .bind(movieThrillerWait())
                .to(moviesExchangeWait())
                .with("movie.thriller.wait")
                .noargs();        
		}
		
		@Bean
        public Binding movieThrillerDeadBinding() {
            return BindingBuilder
                .bind(movieThrillerDead())
                .to(moviesExchangeDead())
                .with("movie.thriller.dead")
                .noargs();        
		}
		
		
		
		
	}
	
	


}

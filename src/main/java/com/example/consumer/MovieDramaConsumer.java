package com.example.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MovieDramaConsumer {

	@RabbitListener(queues = "${conf-queue-exchange.queue-movie-drama}")
	public void listen(String message) {

		log.info("Message {}", message );
		
	}

	
	
}

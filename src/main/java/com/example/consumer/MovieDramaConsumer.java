package com.example.consumer;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Genre;
import com.example.model.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MovieDramaConsumer {

	@RabbitListener(queues = "${conf-queue-exchange.queue-movie-drama}")
	public void listen(Movie messageMovie) {
		
		if(messageMovie.getYear() < 1985)
			throw new AmqpRejectAndDontRequeueException("Reject movie: " + messageMovie.getName());
		else
			log.info("Consumer: {} ", messageMovie.getName());		
	}

	
	
}

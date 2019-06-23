package com.example.consumer;

import java.io.IOException;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Genre;
import com.example.model.Movie;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MovieConsumer {

	private ObjectMapper objectMapper = new ObjectMapper();
	
	@RabbitListener(queues = "${conf-queue-exchange.queue-movie-drama}")
	public void listenDrama(Movie messageMovie) {		
		if(messageMovie.getYear() < 1985)
			throw new AmqpRejectAndDontRequeueException("Reject movie: " + messageMovie.getName());
		else
			log.info("Consumer Drama: {} ", messageMovie.getName());		
	}
	
	@RabbitListener(queues = "${conf-queue-exchange.queue-movie-thriller}")
	public void listenThriller(Message message, Channel channel) throws JsonParseException, JsonMappingException, IOException {
		var movie = objectMapper.readValue(message.getBody(), Movie.class);

		if(movie.getYear() < 1985)
			channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
		
		log.info("Consumer Thriller: {} ", movie.getName());
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}
	
	
	
	
	
	

	
	
}

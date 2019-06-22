package com.example.producer;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.model.Genre;
import com.example.model.Movie;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MovieProducer {

	private RabbitTemplate rabbitTemplate;
	private int countDrama = 0;
	private int countThriller = 0;
	
	@Value("${conf-queue-exchange.exchange-movies}")
	private String moviesExchange;

	@Autowired
	public MovieProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	@Scheduled(fixedRate = 5000)
	public void sendMessageDrama() {		
		var movieDrama = new Movie();
		movieDrama.setName("Movie" + countDrama++);
		movieDrama.setGenre(Genre.DRAMA);
		movieDrama.setYear(ThreadLocalRandom.current().nextInt(1900, 2020));		
		log.info("Send movie drama {}", countDrama);
		rabbitTemplate.convertAndSend(moviesExchange, "movie.drama", movieDrama);
	}
	
	@Scheduled(fixedRate = 500)
	public void sendMessageThriller() {		
		var movieThriller = new Movie();
		movieThriller.setName("Movie" + countThriller++);
		movieThriller.setGenre(Genre.DRAMA);
		movieThriller.setYear(ThreadLocalRandom.current().nextInt(1900, 2020));
		log.info("Send movie thriller {}", countDrama);
		rabbitTemplate.convertAndSend(moviesExchange, "movie.thriller", movieThriller);
	}
	
	
	
}

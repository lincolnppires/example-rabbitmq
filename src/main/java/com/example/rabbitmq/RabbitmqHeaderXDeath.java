package com.example.rabbitmq;

import java.util.Date;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode
public class RabbitmqHeaderXDeath {
	
	private int count;
	private String exchange;
	private String queue;
	private String reason;
	private List<String> routingKeys;
	private Date time;
	
	

}

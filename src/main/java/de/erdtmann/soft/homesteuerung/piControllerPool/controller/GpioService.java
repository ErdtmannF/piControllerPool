package de.erdtmann.soft.homesteuerung.piControllerPool.controller;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;

import ch.qos.logback.classic.Logger;

@Service
public class GpioService {

	Logger log = (Logger) LoggerFactory.getLogger(GpioService.class);
	
	private Context pi4j;

	public GpioService() {
		pi4j = Pi4J.newAutoContext();
	}
//	@PostConstruct
//	public void init() {
//		pi4j = Pi4J.newAutoContext();
//	}

	public Context getContext() {
		return pi4j;
	}
	
	
}

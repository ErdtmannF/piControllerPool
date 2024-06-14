package de.erdtmann.soft.homesteuerung.piControllerPool.controller;

import javax.annotation.PostConstruct;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

import ch.qos.logback.classic.Logger;

@Service
public class GpioService {

	Logger log = (Logger) LoggerFactory.getLogger(GpioService.class);
	
	private GpioController gpio;

	@PostConstruct
	public void init() {
		gpio = GpioFactory.getInstance();
	}

	public GpioController getGpio() {
		return gpio;
	}
	
	
}

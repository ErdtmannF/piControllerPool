package de.erdtmann.soft.homesteuerung.piControllerPool.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import ch.qos.logback.classic.Logger;

@Service
public class TasterController {

	Logger log = (Logger) LoggerFactory.getLogger(TasterController.class);

	private GpioController gpio = null;
	private GpioPinDigitalInput tastereingang = null;

	public TasterController() {
		try {
			gpio = GpioFactory.getInstance();
			if (gpio != null) {
				tastereingang = gpio.provisionDigitalInputPin(RaspiPin.GPIO_07, "Tastereingang", PinPullResistance.PULL_DOWN);
			}

			 tastereingang.addListener(new GpioPinListenerDigital() {
		            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {

		            	log.info(event.getPin() + " / " + event.getState());
		            	
		            }

		        });

			 
		} catch (Exception e) {
			log.warn("Fehler beim initialisieren des Tasten Controllers");
		}

		log.info("Tasten Controller initialisiert");
	}


}

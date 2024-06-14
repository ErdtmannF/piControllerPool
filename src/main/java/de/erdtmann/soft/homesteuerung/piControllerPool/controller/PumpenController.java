package de.erdtmann.soft.homesteuerung.piControllerPool.controller;

import javax.annotation.PostConstruct;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import ch.qos.logback.classic.Logger;

@Controller
public class PumpenController {

	Logger log = (Logger) LoggerFactory.getLogger(PumpenController.class);
	
	@Autowired
	GpioService gpioService;
	
	private GpioPinDigitalOutput pumpenausgang = null;

	@PostConstruct
	public void init() {
		try {
			if (gpioService.getGpio() != null) {
				pumpenausgang = gpioService.getGpio().provisionDigitalOutputPin(RaspiPin.GPIO_01, "Pumpenausgang", PinState.LOW);
				pumpenausgang.setShutdownOptions(true, PinState.LOW);
			}

		} catch (Exception e) {
			log.error("Fehler beim initialisieren des Pumpen Controllers");
			log.error(e.getMessage() );
		}
		log.debug("Pumpen Controller initialisiert");
	}
	
	public void schaltePumpeEin() {
		if (pumpenausgang != null) {
			pumpenausgang.setState(PinState.HIGH);
			
			log.debug("Pumpe eingeschaltet");
		}
	}

	public void schaltePumpeAus() {
		if (pumpenausgang != null) {
			pumpenausgang.setState(PinState.LOW);
			
			log.debug("Pumpe ausgeschaltet");
		}
	}
	
	public int istPumpeAn() {
		if (pumpenausgang.isHigh()) {
			return 1;
		} else {
			return 0;
		}
	}

}

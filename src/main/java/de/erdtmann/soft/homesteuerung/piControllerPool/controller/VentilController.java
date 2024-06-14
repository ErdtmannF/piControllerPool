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
public class VentilController {

	Logger log = (Logger) LoggerFactory.getLogger(VentilController.class);

	@Autowired
	GpioService gpioService;
	
	private GpioPinDigitalOutput ventilausgang = null;

	@PostConstruct
	public void init() {
		try {
			if (gpioService.getGpio() != null) {
				ventilausgang = gpioService.getGpio().provisionDigitalOutputPin(RaspiPin.GPIO_00, "Ventilausgang", PinState.LOW);
				ventilausgang.setShutdownOptions(true, PinState.LOW);
			}

		} catch (Exception e) {
			log.error("Fehler beim initialisieren des Ventil Controllers");
			log.error(e.getMessage());
		}

		log.debug("Ventil Controller initialisiert");
	}
	
	public void ventilAufPool() {
		if (ventilausgang != null) {
			ventilausgang.setState(PinState.LOW);
			
			log.debug("Ventil auf Pool");
		}
	}

	public void ventilAufHeizung() {
		if (ventilausgang != null) {
			ventilausgang.setState(PinState.HIGH);
			
			log.debug("Ventil auf Heizung");
		}
	}

	public int istHeizungAn() {
		if (ventilausgang.isHigh()) {
			return 1;
		} else {
			return 0;
		}
	}
	
}

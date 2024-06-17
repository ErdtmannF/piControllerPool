package de.erdtmann.soft.homesteuerung.piControllerPool.controller;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalOutputConfig;
import com.pi4j.io.gpio.digital.DigitalState;

import ch.qos.logback.classic.Logger;

@Controller
public class PumpenController {

	Logger log = (Logger) LoggerFactory.getLogger(PumpenController.class);
	
//	@Autowired
	private GpioService gpioService;
	
	private static final int PIN_PUMPE = 18;
	
	private DigitalOutputConfig pumpenConfig;
	private DigitalOutput pumpenausgang;

	public PumpenController(@Autowired GpioService gpioService) {
		this.gpioService = gpioService;
		
		init();
	}
	
//	@PostConstruct
	public void init() {
		try {
			if (this.gpioService.getContext() != null) {
				 pumpenConfig = DigitalOutput.newConfigBuilder(gpioService.getContext())
									.id("pumpe")
									.name("Pumpenausgang")
									.address(PIN_PUMPE)
									.shutdown(DigitalState.LOW)
									.initial(DigitalState.LOW)
									.provider("pigpio-digital-output")
									.build();
				pumpenausgang = gpioService.getContext().create(pumpenConfig);
			}

		} catch (Exception e) {
			log.error("Fehler beim initialisieren des Pumpen Controllers");
			log.error(e.getMessage() );
		}
		log.debug("Pumpen Controller initialisiert");
	}
	
	public void schaltePumpeEin() {
		if (pumpenausgang != null) {
			pumpenausgang.high();
			
			log.debug("Pumpe eingeschaltet");
		}
	}

	public void schaltePumpeAus() {
		if (pumpenausgang != null) {
			pumpenausgang.low();
			
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

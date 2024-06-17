package de.erdtmann.soft.homesteuerung.piControllerPool.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalOutputConfig;
import com.pi4j.io.gpio.digital.DigitalState;

import ch.qos.logback.classic.Logger;

@Controller
public class VentilController {

	Logger log = (Logger) LoggerFactory.getLogger(VentilController.class);

//	@Autowired
	private GpioService gpioService;
	
	private static final int PIN_VENTIL = 17;
	
	private DigitalOutputConfig ventilConfig;
	private DigitalOutput ventilausgang;

	public VentilController(@Autowired GpioService gpioService) {
		this.gpioService = gpioService;
		init();
	}
	
//	@PostConstruct
	public void init() {
		try {
			if (this.gpioService.getContext() != null) {
				ventilConfig = DigitalOutput.newConfigBuilder(gpioService.getContext())
						.id("ventil")
						.name("Ventilausgang")
						.address(PIN_VENTIL)
						.shutdown(DigitalState.LOW)
						.initial(DigitalState.LOW)
						.provider("pigpio-digital-output")
						.build();
				ventilausgang = gpioService.getContext().create(ventilConfig);			}

		} catch (Exception e) {
			log.error("Fehler beim initialisieren des Ventil Controllers");
			log.error(e.getMessage());
		}

		log.debug("Ventil Controller initialisiert");
	}
	
	public void ventilAufPool() {
		if (ventilausgang != null) {
			ventilausgang.low();
			
			log.debug("Ventil auf Pool");
		}
	}

	public void ventilAufHeizung() {
		if (ventilausgang != null) {
			ventilausgang.high();
			
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

package de.erdtmann.soft.homesteuerung.piControllerPool.RESTInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.erdtmann.soft.homesteuerung.piControllerPool.controller.TempController;
import de.erdtmann.soft.poolSteuerung.utils.Temperatur;

@RestController
@RequestMapping("/temperatur")
public class TempInterface {

	@Autowired
	TempController tempController;
	
	@GetMapping("/wasser")
	public float holeWasserTemp() {
		return tempController.getTemp(Temperatur.WASSER);
	}
	
	@GetMapping("/solar")
	public float holeSolarTemp() {
		return tempController.getTemp(Temperatur.SOLAR);
	}
}

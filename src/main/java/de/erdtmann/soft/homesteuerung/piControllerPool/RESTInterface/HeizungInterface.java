package de.erdtmann.soft.homesteuerung.piControllerPool.RESTInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.erdtmann.soft.homesteuerung.piControllerPool.controller.VentilController;


@RestController
@RequestMapping("/heizung")
public class HeizungInterface {

	@Autowired
	VentilController ventilController;

	@PostMapping("/ein")
	public void heizungEin() {
		ventilController.ventilAufHeizung();
	}

	@PostMapping("/aus")
	public void heizungAus () {
		ventilController.ventilAufPool();
	}
	
	@GetMapping("/status")
	public int heizungStatus() {
		return ventilController.istHeizungAn();
	}

}

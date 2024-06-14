package de.erdtmann.soft.homesteuerung.piControllerPool.RESTInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.erdtmann.soft.homesteuerung.piControllerPool.controller.PumpenController;


@RestController
@RequestMapping("/pumpe")
public class PumpenInterface {

	@Autowired
	PumpenController pumpenController;
	
	@PostMapping("/ein")
	public void schaltePumpeEin () {
		pumpenController.schaltePumpeEin();
	}
	
	@PostMapping("/aus")
	public void schaltePumpeAus() {
		pumpenController.schaltePumpeAus();
	}
	
	@GetMapping("/status")
	public int pumpenStatus() {
		return pumpenController.istPumpeAn();
	}
}

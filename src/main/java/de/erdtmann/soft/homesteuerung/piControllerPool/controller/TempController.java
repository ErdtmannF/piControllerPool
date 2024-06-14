package de.erdtmann.soft.homesteuerung.piControllerPool.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diozero.devices.PCF8591;

import ch.qos.logback.classic.Logger;
import de.erdtmann.soft.poolSteuerung.utils.Temperatur;

@Service
public class TempController {

	Logger log = (Logger) LoggerFactory.getLogger(TempController.class);

	@Autowired
	PCF8591 adWandler;

	public TempController() {
		log.debug("Temperatur Controller initialisiert");
	}
	
	
	/*
	 * Gibt die gewahlte Temperatur zurück.
	 * Die Temperaturen sind in einem Enum festgelegt
	 */
	public float getTemp(Temperatur temp) {

		// Wert des Sensors
		float value = adWandler.getValue(temp.getValue());

		float volt = berechneSpannung(value);
		
		float rt = berechneWiderstandFuehler(volt); 

		float tempK = berechneTemperaturKelvin(rt);

		float tempC = berechneTemperaturCelsius(tempK);

		log.debug(temp.getBezeichnung() + ": " + tempC);
		
		return tempC;
	}

	/*
	 * Berechnet die Temperatur in Celsius aus der Kelvin Temperatur
	 */
	protected float berechneTemperaturCelsius(float tempK) {
		// Temperatur in Celsius berechnen
		float tempC = (float) (Math.round((tempK - 274.15)* 10.0) / 10.0);
		return tempC;
	}

	/*
	 * Berechnet anhand des Widerstandes die Temperatur in Kelvin
	 */
	protected float berechneTemperaturKelvin(float rt) {
		// Temperatur in Kelvin berechnen
		float tempK = (float) (1 / (1 / (273.15 + 25) + Math.log(rt / 10) / 3950));
		return tempK;
	}

	/*
	 * Berechnet mit der Spannung den Widerstand des Temperaturfuehlers
	 */
	protected float berechneWiderstandFuehler(float volt) {
		// Wiederstand des Fuehlers berechnen
		float rt = (float) (10 * volt / (3.3 - volt));
		return rt;
	}

	/*
	 * Berechnet die Spannung die am Temperaturfuehler abfaellt
	 */
	protected float berechneSpannung(float value) {
		// Spannung berechnen
		float volt = (float) (value * 3.3 / 1);
		return volt;
	}

}

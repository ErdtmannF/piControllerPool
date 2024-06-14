package de.erdtmann.soft.homesteuerung.piControllerPool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.diozero.devices.PCF8591;

@Configuration
public class AppConfiguration {

	@Bean
	public PCF8591 myPCF8591() {
		return new PCF8591();
	}
}

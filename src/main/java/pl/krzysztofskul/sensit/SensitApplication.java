package pl.krzysztofskul.sensit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SensitApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SensitApplication.class, args);
		System.setProperty("java.awt.headless", "false");
	}
	

}

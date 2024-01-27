package pl.krzysztofskul.smnsh2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Smnsh2Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Smnsh2Application.class, args);
		System.setProperty("java.awt.headless", "false");
	}
	

}

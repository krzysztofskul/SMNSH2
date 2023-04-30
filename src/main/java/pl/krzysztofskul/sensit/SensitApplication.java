package pl.krzysztofskul.sensit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SensitApplication {

	public static void main(String[] args) {
		SpringApplication.run(SensitApplication.class, args);
	}
	
	@GetMapping
	public String root() {
		return "sensit root page";
	}

}

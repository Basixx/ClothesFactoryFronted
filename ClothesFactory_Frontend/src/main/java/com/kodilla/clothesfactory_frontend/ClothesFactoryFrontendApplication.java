package com.kodilla.clothesfactory_frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class ClothesFactoryFrontendApplication {

	public static void main(String[] args) {
//		SpringApplication.run(ClothesFactoryFrontendApplication.class, args);
		SpringApplication app = new SpringApplication(ClothesFactoryFrontendApplication.class);
		app.setDefaultProperties(Collections
				.singletonMap("server.port", "9000"));
		app.run(args);
	}

}

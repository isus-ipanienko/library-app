package com.merito.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.merito.app.controllers.HomeController;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
        HomeController.initialize();
		SpringApplication.run(AppApplication.class, args);
	}

}

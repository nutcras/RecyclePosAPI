package com.example.sellWebApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.sellWebApp", "com.example.sellWebApp.controller"})  // Explicitly define packages to scan
public class SellWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SellWebAppApplication.class, args);
	}

}

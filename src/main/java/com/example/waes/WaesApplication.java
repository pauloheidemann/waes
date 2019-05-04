package com.example.waes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.waes")
@EntityScan(basePackages = "com.example.waes.model")
public class WaesApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaesApplication.class, args);
	}

}

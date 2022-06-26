package com.escalab.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@SpringBootApplication()
public class SpringbootGen9BibliotecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootGen9BibliotecaApplication.class, args);
	}

}

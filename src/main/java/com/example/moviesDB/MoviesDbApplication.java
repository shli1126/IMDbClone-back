package com.example.moviesDB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class MoviesDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesDbApplication.class, args);
	}



}

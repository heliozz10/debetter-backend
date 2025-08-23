package com.heliozz10.debetter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity
@EnableCaching
@SpringBootApplication
public class DebetterApplication {

	public static void main(String[] args) {
		SpringApplication.run(DebetterApplication.class, args);
	}

}

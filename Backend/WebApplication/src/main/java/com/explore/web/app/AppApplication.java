package com.explore.web.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

/**
 * This will be base Application file for Spring Boot project
 * @author arpit
 *
 */

@Slf4j
@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
		log.info("Initial run");
		
	}

}

package com.example.SpaceProject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpaceProjectApplication {

	private static final Logger logger = LoggerFactory.getLogger(SpaceProjectApplication.class);
	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpaceProjectApplication.class, args);
		logger.info("Aplikace běží na adrese: http://localhost:{}", applicationContext.getEnvironment().getProperty("local.server.port").concat("/saveAstronauts"));
	}

}

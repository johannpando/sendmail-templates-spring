package com.example.demomailspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// @Bean
	// public MessageSource messageSource() {
	// ReloadableResourceBundleMessageSource messageSource = new
	// ReloadableResourceBundleMessageSource();
	// messageSource.setBasename("classpath:messages");
	// messageSource.setCacheSeconds(10); // reload messages every 10 seconds
	// return messageSource;
	// }

}

package com.example.eventticketingbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// remove this after
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class EventTicketingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventTicketingBackendApplication.class, args);
	}

}

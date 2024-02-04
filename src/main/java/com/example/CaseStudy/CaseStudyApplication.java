package com.example.CaseStudy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.CaseStudy.services.FlightService;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling

public class CaseStudyApplication{

	private final FlightService flightService;

	@Autowired
	public CaseStudyApplication(FlightService flightService) {
		this.flightService = flightService;
	}

	public static void main(String[] args) {
		SpringApplication.run(CaseStudyApplication.class, args);
	}


}

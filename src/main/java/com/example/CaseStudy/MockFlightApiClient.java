package com.example.CaseStudy;

import com.example.CaseStudy.models.flights;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MockFlightApiClient {

    public List<flights> fetchFlightData(int numberOfFlights) {
        // Simulate API request and return mock flight data
        // You can use libraries like ModelMapper or create Flight objects manually

        List<flights> mockFlights = new ArrayList<>();

        for (int i = 1; i <= numberOfFlights; i++) {
            String departureAirport = "Airport" + i;
            String arrivalAirport = "Airport" + (i + 1);
            LocalDateTime departureDateTime = getRandomDateTime();
            LocalDateTime returnDateTime = getRandomDateTimeAfter(departureDateTime);
            long price = i + 142;

            flights flight = new flights(departureAirport, arrivalAirport, departureDateTime, returnDateTime, price);
            mockFlights.add(flight);
        }

        return mockFlights;
    }


    private LocalDateTime getRandomDateTime() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime randomDateTime = now.plusDays(new Random().nextInt(30)); // Random date within the next 30 days
        return randomDateTime;
    }

    private LocalDateTime getRandomDateTimeAfter(LocalDateTime afterDateTime) {
        return afterDateTime.plusHours(new Random().nextInt(48)); // Random date within the next 48 hours after the provided date
    }
}

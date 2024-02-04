package com.example.CaseStudy.services;

import com.example.CaseStudy.MockFlightApiClient;
import com.example.CaseStudy.models.flights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduledFlightUpdateService {

    private final FlightService flightService;
    private final MockFlightApiClient mockFlightApiClient;

    @Autowired
    public ScheduledFlightUpdateService(FlightService flightService, MockFlightApiClient mockFlightApiClient) {
        this.flightService = flightService;
        this.mockFlightApiClient = mockFlightApiClient;
    }

    // Run the scheduled job every day at 2 AM
    @Scheduled(cron = "0 0 2 * * ?")
    public void updateFlightsFromMockApi() {
        int numberOfFlights = 10; // You can adjust this based on your requirements
        List<flights> mockFlights = mockFlightApiClient.fetchFlightData(numberOfFlights);
        flightService.saveMockFlights(mockFlights);
    }
}
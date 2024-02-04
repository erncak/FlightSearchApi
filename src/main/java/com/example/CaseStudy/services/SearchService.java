package com.example.CaseStudy.services;

import com.example.CaseStudy.models.flights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SearchService {

    private final FlightService flightService;

    @Autowired
    public SearchService(FlightService flightService) {
        this.flightService = flightService;
    }
    @PreAuthorize("permitAll")
    public List<flights> searchFlights(String departureAirport, String arrivalAirport, LocalDateTime departureDateTime, LocalDateTime returnDateTime) {
        System.out.println("Hayyyyyde");
        System.out.println("Departure Airport:" +departureAirport);
        System.out.println("ArrivalAirport:"+ arrivalAirport);
        System.out.println("time"+departureDateTime);
        if (returnDateTime != null) {
            // Case: Round-trip flight
            return flightService.findRoundTripFlights(departureAirport, arrivalAirport, departureDateTime, returnDateTime);
        } else {
            // Case: One-way flight
            return flightService.findOneWayFlights(departureAirport, arrivalAirport, departureDateTime);
        }
    }

    @PreAuthorize("permitAll")
    public List<flights> getAllFlights() {
        // Use the appropriate method from FlightService to get all flights
        return flightService.getAllFlights();
    }

}

package com.example.CaseStudy.services;
import com.example.CaseStudy.models.flights;
import com.example.CaseStudy.dao.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<flights> getAllFlights() {
        return flightRepository.findAll();
    }

    public Optional<flights>getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    public flights createFlight(flights flight) {
        return flightRepository.save(flight);
    }

    public flights updateFlight(Long id, flights flight) {
        if (flightRepository.existsById(id)) {
            flight.setId(id);
            return flightRepository.save(flight);
        } else {
            // Handle not found scenario
            return null;
        }
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }
    public List<flights> findRoundTripFlights(String departureAirport, String arrivalAirport, LocalDateTime departureDateTime, LocalDateTime returnDateTime) {
        System.out.println("FlightService Airport:"+ departureAirport);
        return flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureDateTimeAfterAndReturnDateTimeBefore(
                departureAirport, arrivalAirport, departureDateTime, returnDateTime);
    }

    public List<flights> findOneWayFlights(String departureAirport, String arrivalAirport, LocalDateTime departureDateTime) {
        return flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureDateTimeAfter(
                departureAirport, arrivalAirport, departureDateTime);
    }
    public void saveMockFlights(List<flights> mockFlights) {
        System.out.println(mockFlights);
        flightRepository.saveAll(mockFlights);
    }
}


package com.example.CaseStudy.dao;

import com.example.CaseStudy.models.flights;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<flights, Long> {
    List<flights> findByDepartureAirportAndArrivalAirportAndDepartureDateTimeAfterAndReturnDateTimeBefore(
            String departureAirport, String arrivalAirport, LocalDateTime departureDateTime, LocalDateTime returnDateTime);

    List<flights> findByDepartureAirportAndArrivalAirportAndDepartureDateTimeAfter(
            String departureAirport, String arrivalAirport, LocalDateTime departureDateTime);
}

package com.example.CaseStudy.controllers;
import com.example.CaseStudy.models.flights;
import com.example.CaseStudy.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<List<flights>> getAllFlights() {
        List<flights> allFlights = flightService.getAllFlights();
        return ResponseEntity.ok(allFlights);
    }

    @GetMapping("/{id}")
    public ResponseEntity<flights> getFlightById(@PathVariable Long id) {
        Optional<flights> flight = flightService.getFlightById(id);
        return flight.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<flights> createFlight(@RequestBody flights flight) {
        flights createdFlight = flightService.createFlight(flight);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFlight);
    }

    @PutMapping("/{id}")
    public ResponseEntity<flights> updateFlight(@PathVariable Long id, @RequestBody flights flight) {
        flights updatedFlight = flightService.updateFlight(id, flight);
        if (updatedFlight != null) {
            return ResponseEntity.ok(updatedFlight);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/round-trip")
    public ResponseEntity<List<flights>> findRoundTripFlights(
            @RequestParam String departureAirport,
            @RequestParam String arrivalAirport,
            @RequestParam String departureDateTime,
            @RequestParam String returnDateTime) {
        // Convert date strings to LocalDateTime as needed
        List<flights> roundTripFlights = flightService.findRoundTripFlights(
                departureAirport, arrivalAirport, LocalDateTime.parse(departureDateTime), LocalDateTime.parse(returnDateTime));
        return ResponseEntity.ok(roundTripFlights);
    }

    @GetMapping("/one-way")
    public ResponseEntity<List<flights>> findOneWayFlights(
            @RequestParam String departureAirport,
            @RequestParam String arrivalAirport,
            @RequestParam String departureDateTime) {
        // Convert date string to LocalDateTime as needed
        List<flights> oneWayFlights = flightService.findOneWayFlights(
                departureAirport, arrivalAirport, LocalDateTime.parse(departureDateTime));

        return ResponseEntity.ok(oneWayFlights);
    }
}


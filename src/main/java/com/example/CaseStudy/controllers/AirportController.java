package com.example.CaseStudy.controllers;
import com.example.CaseStudy.models.airports;
import com.example.CaseStudy.services.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public ResponseEntity<List<airports>> getAllAirports() {
        List<airports> allAirports = airportService.getAllAirports();
        return ResponseEntity.ok(allAirports);
    }

    @GetMapping("/{id}")
    public ResponseEntity<airports> getAirportById(@PathVariable Long id) {
        Optional<airports> airport = airportService.getAirportById(id);
        return airport.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<airports> createAirport(@RequestBody airports airport) {
        airports createdAirport = airportService.createAirport(airport);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAirport);
    }

    @PutMapping("/{id}")
    public ResponseEntity<airports> updateAirport(@PathVariable Long id, @RequestBody airports airport) {
        airports updatedAirport = airportService.updateAirport(id, airport);
        if (updatedAirport != null) {
            return ResponseEntity.ok(updatedAirport);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Long id) {
        airportService.deleteAirport(id);
        return ResponseEntity.noContent().build();
    }
}


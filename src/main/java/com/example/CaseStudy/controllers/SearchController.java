package com.example.CaseStudy.controllers;

import com.example.CaseStudy.models.flights;
import com.example.CaseStudy.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/flights")
    public ResponseEntity<List<flights>> searchFlights(
            @RequestParam String departureAirport,
            @RequestParam String arrivalAirport,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureDateTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime returnDateTime) {

        List<flights> foundFlights = searchService.searchFlights(departureAirport, arrivalAirport, departureDateTime, returnDateTime);
        return ResponseEntity.ok(foundFlights);
    }
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/allflights")
    public ResponseEntity<List<flights>> getAllFlights() {
        System.out.println("AllFlightsController");
        List<flights> allFlights = searchService.getAllFlights();
        return ResponseEntity.ok(allFlights);
    }
}

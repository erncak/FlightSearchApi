package com.example.CaseStudy.services;

import com.example.CaseStudy.models.airports;
import com.example.CaseStudy.dao.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    @Autowired
    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<airports> getAllAirports() {
        return airportRepository.findAll();
    }

    public Optional<airports> getAirportById(Long id) {
        return airportRepository.findById(id);
    }

    public airports createAirport(airports airport) {
        return airportRepository.save(airport);
    }

    public airports updateAirport(Long id, airports airport) {
        if (airportRepository.existsById(id)) {
            airport.setId(id);
            return airportRepository.save(airport);
        } else {
            // Handle not found scenario
            return null;
        }
    }

    public void deleteAirport(Long id) {
        airportRepository.deleteById(id);
    }
}

package com.example.CaseStudy.dao;

import com.example.CaseStudy.models.airports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<airports, Long> {
    // Additional query methods if needed
}
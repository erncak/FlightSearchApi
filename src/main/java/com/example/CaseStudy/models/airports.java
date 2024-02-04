package com.example.CaseStudy.models;

import jakarta.persistence.*;

@Entity
@Table(name = "airports")
public class airports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="city")
    private String city;


    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}

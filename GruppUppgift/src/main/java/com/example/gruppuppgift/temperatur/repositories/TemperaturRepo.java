package com.example.gruppuppgift.temperatur.repositories;

import com.example.gruppuppgift.temperatur.models.Temperatur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperaturRepo extends JpaRepository<Temperatur, Long> { //Jparepository för inbyggda funktioner
}

package com.example.gruppuppgift.repositories;

import com.example.gruppuppgift.models.Temperatur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemperaturRepo extends JpaRepository<Temperatur, Long> {
}
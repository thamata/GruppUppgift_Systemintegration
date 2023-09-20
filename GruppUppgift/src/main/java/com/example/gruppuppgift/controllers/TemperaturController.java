package com.example.gruppuppgift.controllers;

import com.example.gruppuppgift.models.Temperatur;
import com.example.gruppuppgift.repositories.TemperaturRepo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class TemperaturController {
    protected final TemperaturRepo temperaturrepo;

    TemperaturController(TemperaturRepo temperatur){
        this.temperaturrepo = temperatur;
    }

    @RequestMapping("temperaturer")
    public List<Temperatur> getAllTemperatur(){
        return temperaturrepo.findAll();
    }

}

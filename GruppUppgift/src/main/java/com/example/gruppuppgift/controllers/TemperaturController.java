package com.example.gruppuppgift.controllers;

import org.springframework.ui.Model;
import com.example.gruppuppgift.models.Temperatur;
import com.example.gruppuppgift.repositories.TemperaturRepo;
import org.springframework.web.bind.annotation.GetMapping;
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

    @RequestMapping("/t")
    public List<Temperatur> getAllTemperatur(){
        return temperaturrepo.findAll();
    }

    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("listTemp", temperaturrepo.findAll());
        return "index";
    }

}

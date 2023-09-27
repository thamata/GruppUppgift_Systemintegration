package com.example.gruppuppgift.temperatur.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.gruppuppgift.temperatur.models.Temperatur;
import com.example.gruppuppgift.temperatur.repositories.TemperaturRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class TemperaturController {
    protected final TemperaturRepo temperaturrepo;

    TemperaturController(TemperaturRepo temperatur){
        this.temperaturrepo = temperatur;
    }

    //@RequestMapping("/t")
    //public List<Temperatur> getAllTemperatur(){return temperaturrepo.findAll();}

    @RequestMapping("/all")
    public String getAll(Model model){
        List<Temperatur> local = temperaturrepo.findAll();
        model.addAttribute("allTemp", local);
        return "showAllTemp";
    }

}

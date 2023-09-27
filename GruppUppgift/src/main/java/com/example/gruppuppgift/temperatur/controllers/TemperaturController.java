package com.example.gruppuppgift.temperatur.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.gruppuppgift.temperatur.models.Temperatur;
import com.example.gruppuppgift.temperatur.repositories.TemperaturRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class TemperaturController {
    protected final TemperaturRepo temperaturrepo;

    TemperaturController(TemperaturRepo temperatur){ //instansiering av interface TemperaturRepo -> Klass Temperatur + JpaRepository funktioner
        this.temperaturrepo = temperatur;
    }

    //Behöver ingen mapping för default landing page, default är redan index.html för "/"


    @RequestMapping("/all")     //display av all data
    public String getAll(Model model){
        List<Temperatur> local = temperaturrepo.findAll(); //variabel för utdata från sql
        model.addAttribute("allTemp", local); //variabel för thymeleaf
        return "showAllTemp"; //pekar på html filen
    }

    @RequestMapping(path = "/deleteById/{id}")
    public String deleteTemp(@PathVariable Long id, Model model) { //funktion för att ta bort id, inparameter hanteras i html
        temperaturrepo.deleteById(id); //tar bort data med id
        return getAll(model); //refreshar sidan, samma som att anropa /all i webbläsaren
    }
}

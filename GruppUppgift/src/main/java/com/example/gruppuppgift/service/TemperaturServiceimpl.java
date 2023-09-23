package com.example.gruppuppgift.service;

import com.example.gruppuppgift.models.Temperatur;
import com.example.gruppuppgift.repositories.TemperaturRepo;

import java.util.List;

public class TemperaturServiceimpl implements TemperaturService{
    protected TemperaturRepo temprepo;

    @Override
    public List<Temperatur> getAllTemperatur(){
        return temprepo.findAll();
    }
}

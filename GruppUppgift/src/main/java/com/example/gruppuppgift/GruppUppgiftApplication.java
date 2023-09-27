package com.example.gruppuppgift;

import com.example.gruppuppgift.temperatur.arduino.Arduino;
import com.example.gruppuppgift.temperatur.repositories.TemperaturRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GruppUppgiftApplication {

    public static void main(String[] args) {
        SpringApplication.run(GruppUppgiftApplication.class, args);
    }


    @Bean
    public CommandLineRunner demo(TemperaturRepo tempRepo){
        return args ->{
            Arduino arduino = new Arduino(tempRepo);
            arduino.start();
        };
    }
}

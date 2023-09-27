package com.example.gruppuppgift.temperatur.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Temperatur {
    @Id
    @GeneratedValue
    protected Long id; //id med auto increment

    protected String temp; //variabel för temperatur data, lättare att hantera om det är en string, behöver inte konvertera datatyp hela tiden

    //protected Timestamp datum;
    protected String datum; //Försökte att lagra som date datatyp i sql, blev String av samma anledning som ovan

    public Temperatur(String temp, String datum){ //konstruktor utan id
        this.temp = temp;
        this.datum = datum;
    }
}

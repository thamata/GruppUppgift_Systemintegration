package com.example.gruppuppgift.models;

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
    protected Long id;

    protected String temp;

    //protected Timestamp datum;
    protected String datum;

    public Temperatur(String temp, String datum){
        this.temp = temp;
        this.datum = datum;
    }
}

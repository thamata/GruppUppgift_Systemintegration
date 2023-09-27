package com.example.gruppuppgift.arduino; 

import com.example.gruppuppgift.models.Temperatur;
import com.example.gruppuppgift.repositories.TemperaturRepo;
import com.fazecast.jSerialComm.SerialPort;
import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.time.LocalDateTime;


@Data
public class Arduino extends Thread{
    protected Scanner data;
    protected final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    protected final TemperaturRepo temprepo;

    public Arduino(TemperaturRepo temprepo){
        this.temprepo = temprepo;
    }
    public void run(){
        SerialPort port= SerialPort.getCommPort("COM7");

        if(!port.openPort()){
            System.out.println("Port is unable to open");

        }

        port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING,0,0);
        data= new Scanner(port.getInputStream());

        while(data.hasNextLine()){
            Temperatur temp = new Temperatur();
            Date date = new Date();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println(data.nextLine());
            System.out.println(new Timestamp(date.getTime()));

            temp.setTemp(data.nextLine());
            temp.setDatum(sdf1.format(timestamp));

            try {
                temprepo.save(temp);
                System.out.println("Saved to the database: " + temp.getTemp());
            } catch (Exception e) {
                System.err.println("Error saving to the database: " + e.getMessage());
                e.printStackTrace();
            }

        }

    }
}

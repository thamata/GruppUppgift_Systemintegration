package com.example.gruppuppgift.temperatur.arduino;

import com.example.gruppuppgift.temperatur.models.Temperatur;
import com.example.gruppuppgift.temperatur.repositories.TemperaturRepo;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener; //https://github.com/Fazecast/jSerialComm/wiki/Modes-of-Operation#event-driven-callback-mode
import com.fazecast.jSerialComm.SerialPortEvent;
import lombok.Data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Scanner;


@Data
public class Arduino extends Thread{
    protected Scanner data; //Text scanner för att läsa output från arduino
    protected final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //specifiering av dataformat

    protected final TemperaturRepo temprepo;

    public Arduino(TemperaturRepo temprepo){ //instansiering av interface TemperaturRepo -> Klass Temperatur + JpaRepository funktioner
        this.temprepo = temprepo;
    }
    public void run(){ //denna metod körs automatiskt så fort en ny tråd är skapad
        SerialPort port= SerialPort.getCommPort("COM3"); //OBS port kan variera
        SerialPortDataListener listener = new SerialPortDataListener() { //hantering om port stängs
            @Override
            public int getListeningEvents() {
                return port.LISTENING_EVENT_PORT_DISCONNECTED; //Skapar ett nytt serialEvent om vi tappar kontakt med porten
            }
            @Override
            public void serialEvent(SerialPortEvent event) {
                if (event.getEventType() == port.LISTENING_EVENT_PORT_DISCONNECTED) {
                    System.err.println("Arduino DC");
                    port.closePort(); //om vi tappar kontakt med porten, stäng den
                    data.close(); //TODO stäng ner programmet
                }
            }
        };

        if(!port.openPort()){ //Om vi inte kan kontakta porten
            System.out.println("Port is unable to open");
        }

        port.addDataListener(listener); //Lägger till listener på nuvarande port
        port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING,0,0); // "The TIMEOUT_READ_SEMI_BLOCKING mode specifies that a corresponding read call will block until either newReadTimeout milliseconds of inactivity have elapsed or at least 1 byte of data can be read."
        data= new Scanner(port.getInputStream()); //öppna scanner

        while(data.hasNextLine()){ //true så länge arduino fortsätter skicka data
            Temperatur temp = new Temperatur();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            //System.out.println(data.nextLine()); //DEBUG
            //System.out.println(new Timestamp(date.getTime())); //DEBUG

            temp.setTemp(data.nextLine()); //Bug där temp blir: 26.26.25 // 2525.76 // Fixat genom att ändra plats på delay i arduino kod
            temp.setDatum(sdf1.format(timestamp)); //sparar nuvarande datum för lagring i DB

            try {
                temprepo.save(temp);
                System.out.println("Saved to the database: " + temp.getTemp());
            } catch (Exception e) {
                System.err.println("Error saving to the database: " + e.getMessage());
                e.printStackTrace();
                data.close();
            }
        }
    }
}
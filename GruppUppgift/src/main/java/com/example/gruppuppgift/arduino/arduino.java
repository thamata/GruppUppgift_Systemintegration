package com.example.newtry.arduino;


import com.fazecast.jSerialComm.SerialPort;
import lombok.Data;

import java.util.Scanner;
@Data
public class Arduino {
    public void connect(){
        SerialPort port= SerialPort.getCommPort("COM3");

        if(!port.openPort()){
            System.out.println("Port is unable to open");

        }
        port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING,0,0);
        Scanner data= new Scanner(port.getInputStream());
        while(data.hasNextLine()){
            System.out.println(data.hasNextLine());

        }

    }
}
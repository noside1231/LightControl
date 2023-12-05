/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edisongrauman.lightcontrol;
import ch.bildspur.artnet.ArtNetBuffer;
import ch.bildspur.artnet.ArtNetClient;

/**
 *
 * @author Edison Grauman
 */
public class ArtNetInstance {
   
    ArtNetClient artnet;
    ArtNetBuffer buffer;

    int serverPort = 8000;
    int clientPort = 8000;
    String ipAddress;
    
    String subnet;
    int universe;
    
    public ArtNetInstance(String ipAddress, int serverPort, int clientPort) {
        this.ipAddress = ipAddress;
        this.serverPort = serverPort;
        this.clientPort = clientPort;
        
       buffer = new ArtNetBuffer();
       artnet = new ArtNetClient(buffer, serverPort, clientPort);
       
//       start();
       
    }
    
    public void unicastDmx(byte[] buffer) {
        artnet.unicastDmx(ipAddress, 0, 0, buffer);

    }
    
    public void start() {
        artnet.start();
        System.out.println("Artnet Started");
    }
    
    public void stop() {
        artnet.stop();
        System.out.println("Artnet Stopped");

    }
    
    
    
}

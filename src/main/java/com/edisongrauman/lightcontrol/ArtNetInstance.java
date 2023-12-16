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
public class ArtNetInstance implements Instance{

    ArtNetClient artnet;
    ArtNetBuffer buffer;

    int serverPort;
    int clientPort;
    String ipAddress;
    
    int instanceID;
    

//    String subnet;
//    int universe;
    public ArtNetInstance(String ipAddress, String serverPort, String clientPort) {
        this.ipAddress = ipAddress;
        this.serverPort = Integer.parseInt(serverPort);
        this.clientPort = Integer.parseInt(clientPort);
    }
    public ArtNetInstance(Module m, int instanceID) {
        System.out.println("New Artnet Instance");
        ipAddress = m.getArtnetIPAddress();
        serverPort = Integer.parseInt(m.getArtnetSourcePort());
        clientPort = Integer.parseInt(m.getArtnetDestinationPort());
        this.instanceID = instanceID;
    }

    public void unicastDmx(byte[] buffer) {
        artnet.unicastDmx(ipAddress, 0, 0, buffer);

    }

    public boolean start() throws Error {

        try {
            System.out.println(ipAddress);
            buffer = new ArtNetBuffer();
            artnet = new ArtNetClient(buffer, serverPort, clientPort);
            artnet.start();
            System.out.println("Artnet Started");
        } catch (Exception e) {
            throw new Error();
        }
        return artnet.isRunning();
    }

    public boolean stop() {
        artnet.stop();
        System.out.println("Artnet Stopped");
        return false;
    }
    
    public void sendPacket() {
        byte[] b = {1, 2, 3, 4};
        artnet.unicastDmx(ipAddress, 0, 0, b);
    }
    
    public int getID() {
        return instanceID;
    }

}

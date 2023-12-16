/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edisongrauman.lightcontrol;

import java.util.ArrayList;

/**
 *
 * @author Edison Grauman
 */
public class Module {

    int instanceID;

    String name;
    private OutputTypes outputType;

    private ArrayList<Fixture> fixtures = new ArrayList<>();

    //Artnet Settings
    private String artnetIPAddress;
    private String artnetSourcePort;
    private String artnetDestinationPort;

    //OSC Settings
    private String oscSourcePort;
    private String oscDestinationPort;

    public Module() {
        System.out.println("Module Created");
        name = "Module";

        outputType = OutputTypes.NONE;
    }

    public void setOutputType(OutputTypes type) {
        outputType = type;
    }

    public OutputTypes getOutputType() {
        return outputType;
    }

    public void addFixture() {
        fixtures.add(new Fixture());
    }

    public void removeFixture(int id) {
        fixtures.remove(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("Setting module name to: " + name);
        this.name = name;
    }

    public Fixture getLastFixture() {
        return fixtures.get(fixtures.size() - 1);
    }

    public ArrayList<Fixture> getFixtures() {
        return fixtures;
    }

    public void setFixtures(ArrayList<Fixture> fixtures) {
        this.fixtures = fixtures;
    }

    public String getArtnetIPAddress() {
        return artnetIPAddress;
    }

    public void setArtnetIPAddress(String artnetIPAddress) {
        System.out.println("Set Artnet IP Address in Module");

        this.artnetIPAddress = artnetIPAddress;
    }

    public String getArtnetSourcePort() {
        return artnetSourcePort;
    }

    public void setArtnetSourcePort(String artnetSourcePort) {
        System.out.println("Set Artnet Source Port in Module");

        this.artnetSourcePort = artnetSourcePort;
    }

    public String getArtnetDestinationPort() {
        return artnetDestinationPort;
    }

    public void setArtnetDestinationPort(String artnetDestinationPort) {
        System.out.println("Set Artnet Dest Port in Module");

        this.artnetDestinationPort = artnetDestinationPort;
    }

    public String getOscSourcePort() {
        return oscSourcePort;
    }

    public void setOscSourcePort(String oscSourcePort) {
        System.out.println("Set OSC Source Port in Module");

        this.oscSourcePort = oscSourcePort;
    }

    public String getOscDestinationPort() {
        return oscDestinationPort;
    }

    public void setOscDestinationPort(String oscDestinationPort) {
        System.out.println("Set OSC Dest Port in Module");
        this.oscDestinationPort = oscDestinationPort;
    }

    public void setInstanceID(int id) {
        instanceID = id;
    }
    public int getInstanceID() {
        return instanceID;
    }

}

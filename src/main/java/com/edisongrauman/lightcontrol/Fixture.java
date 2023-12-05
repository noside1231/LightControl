/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edisongrauman.lightcontrol;

/**
 *
 * @author Edison Grauman
 */
public class Fixture {

    private String name;
    private String universe;
    private String numChannels;
    private String startChannel;

    public Fixture() {
        System.out.println("Fixture created");
        name = "Fixture";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("Fixture name set in Fixture");
        this.name = name;
    }

    public String getUniverse() {
        
        return universe;
    }

    public void setUniverse(String universe) {
                System.out.println("universe set in Fixture");

        this.universe = universe;
    }

    public String getNumChannels() {
        return numChannels;
    }

    public void setNumChannels(String numChannels) {
                System.out.println("num channels set in Fixture");

        this.numChannels = numChannels;
    }

    public String getStartChannel() {
        return startChannel;
    }

    public void setStartChannel(String startChannel) {
                System.out.println("Start channel set in Fixture");

        this.startChannel = startChannel;
    }
}

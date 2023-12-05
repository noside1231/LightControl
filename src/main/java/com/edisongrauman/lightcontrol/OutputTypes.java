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
public enum OutputTypes {
    NONE,
    ARTNET,
    OSC;

    public String getName() {
        switch (this) {
            case NONE:
                return "None";
            case ARTNET:
                return "Artnet";
            case OSC:
                return "OSC";
            default:
                return null;
        }
    }

    public static ArrayList<String> getOutputTypes() {
        ArrayList<String> outputTypes = new ArrayList<>();
        outputTypes.add("None");
        outputTypes.add("Artnet");
        outputTypes.add("OSC");
        return outputTypes;
    }

}

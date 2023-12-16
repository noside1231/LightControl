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
public class InstanceController {

    private ArrayList<Instance> instances;

    private int idCounter;

    public InstanceController() {
        idCounter = 0;
        instances = new ArrayList<>();

    }

    public int newInstance(Module m) {

        System.out.println("New Instance");
        int newID = generateID();
        switch (m.getOutputType()) {
            case ARTNET:
                ArtNetInstance artnetInstance = new ArtNetInstance(m, newID);
                instances.add(artnetInstance);
            default:
        }

        try {
            instances.get(instances.size() - 1).start();
            return newID;
        } catch (Exception E) {
        }
        return -1;
    }

    public void deleteInstance() {

    }

    public void sendPacket() {
        instances.get(0).sendPacket();
    }

    public boolean isInstanceRunning(int id) {
        for (Instance instance : instances) {
            if (instance.getID() == id) {
                return true;
            }
        }
        return false;
    }

    private int generateID() {
        idCounter = idCounter + 1;
        return idCounter;
    }

}

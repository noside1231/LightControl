/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edisongrauman.lightcontrol;

/**
 *
 * @author Edison Grauman
 */
public interface Instance {

    public boolean start();

    public boolean stop();
    
    public void sendPacket();
        
    public int getID();

////    OutputTypes outputType;
//    private Module module;
//    boolean instanceRunning;
//
//    private ArtNetInstance artnetInstance;
//
//    public Instance(Module m) {
//        module = m;
//        instanceRunning = false;
//
//        if (m.getOutputType() == OutputTypes.ARTNET) {
//            System.out.println("Artnet Instance Created");
//
//            artnetInstance = new ArtNetInstance(m.getArtnetIPAddress(), m.getArtnetSourcePort(), m.getArtnetDestinationPort());
//        }
//    }
//
//    public boolean startInstance() {
//        switch (module.getOutputType()) {
//            case NONE:
//                break;
//            case ARTNET:
//                startArtnetInstance();
//                break;
//            default:
//                return false;
//
//        }
//        return instanceRunning;
//    }
//
//    private void startArtnetInstance() {
//        System.out.println("Starting ArtnetInstance");
//        instanceRunning = artnetInstance.start();
//
//    }
//
//    public boolean isRunning() {
//        return instanceRunning;
//    }
}

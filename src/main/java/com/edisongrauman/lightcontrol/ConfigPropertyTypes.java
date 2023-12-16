/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edisongrauman.lightcontrol;

/**
 *
 * @author Edison Grauman
 */
public enum ConfigPropertyTypes {
    NONE,
    MODULE_NAME,
    FIXTURE_NAME,
    NEW_MODULE,
    DELETE_MODULE,
    NEW_FIXTURE,
    DELETE_FIXTURE,
    OUTPUT_OPTION,
    ARTNET_IP_ADDRESS,
    ARTNET_SOURCE_PORT,
    ARTNET_DESTINATION_PORT,
    OSC_SOURCE_PORT,
    OSC_DESTINATION_PORT,
    FIXTURE_UNIVERSE,
    FIXTURE_CHANNEL_START,
    FIXTURE_CHANNEL_COUNT,
    START_STOP_MODULE,
    TEST_ARTNET_PACKET;

//    public String getName() {
//        switch (this) {
//            case NONE:
//                return "NONE";
//            case MODULE_NAME:
//                return "MODULE_NAME";
//            case FIXTURE_NAME:
//                return "FIXTURE_NAME";
//            default:
//                return null;
//        }
//    }
}

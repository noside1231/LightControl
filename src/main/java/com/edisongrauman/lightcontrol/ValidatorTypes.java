/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edisongrauman.lightcontrol;

/**
 *
 * @author Edison Grauman
 */
public enum ValidatorTypes {
    NONE,
    IP,
    NUM_RANGE,
    TEST;
    
    public String getName() {
        switch (this) {
            case NONE:
                return "None";
            case IP:
                return "IP";
            case TEST: 
                return "TEST";
            default:
                return null;
        }
    }
    
}

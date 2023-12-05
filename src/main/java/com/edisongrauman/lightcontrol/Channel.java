/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edisongrauman.lightcontrol;

/**
 *
 * @author Edison Grauman
 */
public class Channel {
    
    String name = "Channel";
    int val = 0;
    int id = 1;
    
    public Channel(int id) {
        this.id = id;
        System.out.println("Channel created with id:"+id);
    }
}

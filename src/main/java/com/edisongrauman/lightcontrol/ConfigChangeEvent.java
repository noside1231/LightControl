/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edisongrauman.lightcontrol;

import javafx.beans.property.SimpleBooleanProperty;

/**
 *
 * @author Edison Grauman
 */
public class ConfigChangeEvent {

    private SimpleBooleanProperty changed;

    private int moduleInd;
    private int fixtureInd;

    private ConfigPropertyTypes pType;

    private String eventVal;

    public ConfigChangeEvent() {
        changed = new SimpleBooleanProperty(false);
        moduleInd = -1;
        fixtureInd = -1;
        pType = ConfigPropertyTypes.NONE;
        eventVal = "";

    }

//    public void setModuleIndex(int ind) {
//        moduleInd = ind;
//    }
    public int getModuleIndex() {
        return moduleInd;
    }
//
//    public void setFixtureIndex(int ind) {
//        fixtureInd = ind;
//    }

    public int getFixtureIndex() {
        return fixtureInd;
    }

//    public void setPropertyType(ConfigPropertyTypes type) {
//        pType = type;
//    }
    public ConfigPropertyTypes getPropertyType() {
        return pType;
    }

    public String getValue() {
        return eventVal;
    }

    public void setEvent(ConfigPropertyTypes propertyType, int moduleIndex, int fixtureIndex, String val) {
        pType = propertyType;
        moduleInd = moduleIndex;
        fixtureInd = fixtureIndex;
        eventVal = val;
        changed.set(!changed.get());
    }

    public void setEvent(ConfigPropertyTypes propertyType, int moduleIndex) {
        setEvent(propertyType, moduleIndex, -1, "");
    }
    
    public void setEvent(ConfigPropertyTypes propertyType, int moduleIndex, int fixtureIndex) {
        setEvent(propertyType, moduleIndex, fixtureIndex, "");
    }

    public void setEvent(ConfigPropertyTypes propertyType, int moduleIndex, String val) {
        setEvent(propertyType, moduleIndex, -1, val);
    }

    public void setEvent(ConfigPropertyTypes propertyType, String val) {
        setEvent(propertyType, -1, -1, val);
    }
    
    public void setEvent(ConfigPropertyTypes propertyType) {
        setEvent(propertyType, -1, -1, "");
    }
    
    public void setEvent(ConfigChangeEvent ev) {
        setEvent(ev.getPropertyType(), ev.getModuleIndex(), ev.getFixtureIndex(), ev.getValue());
    }

    public SimpleBooleanProperty isChanged() {
        return changed;
    }

    @Override
    public String toString() {
        return "ConfigChangeEvent... " + pType.toString() + ", Module: " + moduleInd + ", Fixture: " + fixtureInd + ", Value: " + eventVal;
    }

}

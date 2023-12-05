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
public class ModuleContainer {
    private ArrayList<Module> modules;
    
    ModuleContainer() {
        modules = new ArrayList<>();
    }
    
    public void addModule() {
        modules.add(new Module());
    }
    
    public void removeModule(int i) {
        modules.remove(i);
    }
    
    public Module getModule(int i) {
        return modules.get(i);
    }
    
    public Module getLastModule() {
        return modules.get(modules.size()-1);
    }
    
    public int getModuleCount() {
        return modules.size();
    }
    
    public void setModuleName(int i, String name) {
        modules.get(i).setName(name);
    }
    
    public void addFixture(int i) {
        modules.get(i).addFixture();
    }
    
    public void removeFixture (int moduleIndex, int fixtureIndex) {
        modules.get(moduleIndex).removeFixture(fixtureIndex);
    }
    
    public Fixture getLastFixture(int i) {
        return modules.get(i).getLastFixture();
    }
    
    public void load(ModuleContainer mc) {
        modules = mc.modules;
    }
    
    public ArrayList<Module> getModules() {
        return modules;
    }
    
    public void setArtnetIPAddress(int i, String ip) {
        modules.get(i).setArtnetIPAddress(ip);
    }
    
    public void setArtnetSourcePort(int i, String sourcePort) {
        modules.get(i).setArtnetSourcePort(sourcePort);
    }
    public void setArtnetDestinationPort(int i, String destPort) {
        modules.get(i).setArtnetDestinationPort(destPort);
    }
    public void setOSCSourcePort(int i , String sourcePort) {
        modules.get(i).setOscSourcePort(sourcePort);
    }
    public void setOSCDestinationPort(int i, String destPort) {
        modules.get(i).setOscDestinationPort(destPort);
    }
    public void setOutputOption(int i, String option) {
        modules.get(i).setOutputType(OutputTypes.valueOf(option.toUpperCase()));
    }
    public void setFixtureName(int module_ind, int fixture_ind, String name) {
        modules.get(module_ind).getFixtures().get(fixture_ind).setName(name);
    }
    public void setFixtureUniverse(int module_ind, int fixture_ind, String universe) {
        modules.get(module_ind).getFixtures().get(fixture_ind).setUniverse(universe);
    }
    public void setFixtureChannelStart(int module_ind, int fixture_ind, String channel) {
        modules.get(module_ind).getFixtures().get(fixture_ind).setStartChannel(channel);
    }
    public void setFixtureChannelCount(int module_ind, int fixture_ind, String channel) {
        modules.get(module_ind).getFixtures().get(fixture_ind).setNumChannels(channel);
    }
    
    
        
}

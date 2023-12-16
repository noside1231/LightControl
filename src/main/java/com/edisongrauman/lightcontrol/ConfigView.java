/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edisongrauman.lightcontrol;

import java.util.ArrayList;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Edison Grauman
 */
public class ConfigView extends ScrollPane {

    private final VBox configVBox;
    private final VBox moduleVBox;

    private ConfigChangeEvent changeEventHandler;

    private ArrayList<ModuleConfigView> moduleConfigViews;

    private Button newModuleButton;
    private int lastChangedModuleIndex = -1;
    private int lastChangedFixtureIndex = -1;
    

    public ConfigView() {
        configVBox = new VBox();
        setContent(configVBox);

        moduleVBox = new VBox();

        moduleConfigViews = new ArrayList<>();

        changeEventHandler = new ConfigChangeEvent();

        setupNewModuleButton();
        configVBox.getChildren().addAll(newModuleButton, moduleVBox);

    }

    public void refreshConfigView(ModuleContainer mc) {
        moduleVBox.getChildren().removeAll(moduleVBox.getChildren());
        moduleConfigViews = new ArrayList<>();
        for (Module module : mc.getModules()) {
            addModuleConfigView(module);
        }
    }

    private void setupNewModuleButton() {
        newModuleButton = new Button("New Module");
        newModuleButton.setOnAction(e -> {
            changeEventHandler.setEvent(ConfigPropertyTypes.NEW_MODULE);
        });

    }

    private ModuleConfigView getLastModuleConfigView() {
        return moduleConfigViews.get(moduleConfigViews.size() - 1);
    }

    public void addModuleConfigView(Module module) {
        ModuleConfigView m = new ModuleConfigView(module);
        moduleConfigViews.add(m);
        System.out.println("ModuleView index: " + moduleConfigViews.indexOf(m));

        getLastModuleConfigView().getChangeEvent().isChanged().addListener(cl -> {
            ConfigChangeEvent ce = m.getChangeEvent();
            System.out.println(ce.getPropertyType().toString());
            changeEventHandler.setEvent(ce.getPropertyType(), moduleConfigViews.indexOf(m), ce.getFixtureIndex(), ce.getValue());
        });
        getLastModuleConfigView().getModuleName().addListener(cl -> {
            changeEventHandler.setEvent(ConfigPropertyTypes.MODULE_NAME, moduleConfigViews.indexOf(m), m.getModuleName().get());
        });

        moduleVBox.getChildren().add(getLastModuleConfigView());
    }

    void removeModuleConfigView(int i) {
        moduleVBox.getChildren().remove(i);
        moduleConfigViews.remove(i);
    }

    ModuleConfigView getModuleConfigView(int i) {
        return moduleConfigViews.get(i);
    }

    public int getLastChangedModuleIndex() {
        return lastChangedModuleIndex;
    }

    public int getLastChangedFixtureIndex() {
        return lastChangedFixtureIndex;
    }

    public void addFixture(int i, Fixture fixture) {
        moduleConfigViews.get(i).addFixture(fixture);
    }

    public void removeFixture(int moduleIndex, int fixtureIndex) {
        moduleConfigViews.get(moduleIndex).removeFixture(fixtureIndex);
    }
    
    public void setModuleEdit(int moduleIndex, boolean b) {
        moduleConfigViews.get(moduleIndex).setModuleEdit(b);
    }

    public ConfigChangeEvent getChangeEvent() {
        return changeEventHandler;
    }

}

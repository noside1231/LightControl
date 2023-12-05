/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edisongrauman.lightcontrol;

import java.util.ArrayList;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Edison Grauman
 */
public class ModuleConfigView extends TitledPane {

    private ConfigChangeEvent changeEventHandler;

    private Button newFixtureButton;
    private Button deleteModuleButton;

    private ArrayList<FixtureConfigView> fixtureConfigViews;

    private final VBox configVBox;
    private final VBox fixtureVBox;

    private OutputConfigViewer outputConfigViewer;

    private int lastChangedFixtureIndex = -1;

    private TextFieldLabel name;


    public ModuleConfigView(Module module) {
        changeEventHandler = new ConfigChangeEvent();

        fixtureConfigViews = new ArrayList<>();

        fixtureVBox = new VBox();
        configVBox = new VBox();
        setContent(configVBox);
        setText(module.getName());
        name = new TextFieldLabel(module.getName(), "Module");

        newFixtureButton = new Button("New Fixture");
        newFixtureButton.setOnAction(eh -> {
            changeEventHandler.setEvent(ConfigPropertyTypes.NEW_FIXTURE);
        });
        deleteModuleButton = new Button("Delete Module");
        deleteModuleButton.setOnAction(eh -> {
            changeEventHandler.setEvent(ConfigPropertyTypes.DELETE_MODULE);
        });

        outputConfigViewer = new OutputConfigViewer(module);
        outputConfigViewer.getChangeEvent().isChanged().addListener(cl -> {
            changeEventHandler.setEvent(outputConfigViewer.getChangeEvent());
        });
        outputConfigViewer.getOutputOption().addListener(cl -> {
            changeEventHandler.setEvent(ConfigPropertyTypes.OUTPUT_OPTION, outputConfigViewer.getOutputOption().getValue());
        });

        configVBox.getChildren().add(name);
        configVBox.getChildren().add(deleteModuleButton);

        configVBox.getChildren().add(outputConfigViewer);
        configVBox.getChildren().add(new Label("Fixtures"));
        configVBox.getChildren().add(newFixtureButton);
        configVBox.getChildren().add(fixtureVBox);

        this.textProperty().bind(name.getlastValidatedString());
        
    }

    public void addFixture(Fixture fixture) {
        FixtureConfigView f = new FixtureConfigView(fixture);

        fixtureConfigViews.add(f);
        fixtureVBox.getChildren().add(f);

        f.getFixtureDeleteButtonPressed().addListener(cl -> {
            lastChangedFixtureIndex = fixtureConfigViews.indexOf(f);
            System.out.println("Delete Fixture Button Pressed");
            changeEventHandler.setEvent(ConfigPropertyTypes.DELETE_FIXTURE, -1, lastChangedFixtureIndex);

        });
        f.getFixtureName().addListener(cl -> {
            lastChangedFixtureIndex = fixtureConfigViews.indexOf(f);
            System.out.println("fixture name changed in module config view");
            changeEventHandler.setEvent(ConfigPropertyTypes.FIXTURE_NAME, -1, lastChangedFixtureIndex, f.getFixtureName().get());
        });
        f.getFixtureUniverse().addListener(cl -> {
            lastChangedFixtureIndex = fixtureConfigViews.indexOf(f);
            System.out.println("fixture universe changed in module config view");
            changeEventHandler.setEvent(ConfigPropertyTypes.FIXTURE_UNIVERSE, -1, lastChangedFixtureIndex, f.getFixtureUniverse().get());

        });
        f.getFixtureChannelCount().addListener(cl -> {
            lastChangedFixtureIndex = fixtureConfigViews.indexOf(f);
            System.out.println("fixture channel count changed in module config view");
            changeEventHandler.setEvent(ConfigPropertyTypes.FIXTURE_CHANNEL_COUNT, -1, lastChangedFixtureIndex, f.getFixtureChannelCount().get());

        });
        f.getFixtureChannelStart().addListener(cl -> {
            lastChangedFixtureIndex = fixtureConfigViews.indexOf(f);
            System.out.println("fixture channel start changed in module config view");
            changeEventHandler.setEvent(ConfigPropertyTypes.FIXTURE_CHANNEL_START, -1, lastChangedFixtureIndex, f.getFixtureChannelStart().get());

        });

    }

    public void removeFixture(int fixtureIndex) {
        fixtureVBox.getChildren().remove(fixtureIndex);
        fixtureConfigViews.remove(fixtureIndex);
    }

    public SimpleStringProperty getModuleName() {
        return name.getlastValidatedString();
    }

    public void setModuleName(String name) {
        setText(name);
        this.name.setText(name);
    }

    public int getLastChangedFixtureIndex() {
        return lastChangedFixtureIndex;
    }

    public ConfigChangeEvent getChangeEvent() {
        return changeEventHandler;
    }
    
    public void setModuleEdit(boolean b) {
        name.setDisable(!b);
        deleteModuleButton.setDisable(!b);
        for (FixtureConfigView fixtureConfigView : fixtureConfigViews) {
        fixtureConfigView.setFixtureEdit(b);
        }
    }

}

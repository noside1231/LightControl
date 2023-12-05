/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edisongrauman.lightcontrol;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.controlsfx.control.ToggleSwitch;
/**
 *
 * @author Edison Grauman
 */
public class OutputConfigViewer extends VBox {

    private ObservableList<String> options;

    private SimpleStringProperty outputOptionChanged;

    private ComboBox comboBox;
    private Button startStopButton;
    
    private ToggleSwitch ts;

    private ArtNetConfigViewer artnetOptions;
    private OSCConfigViewer oscOptions;

    private Pane outputOptionPane;

    private ConfigChangeEvent changeEventHandler;

    public OutputConfigViewer(Module m) {

        changeEventHandler = new ConfigChangeEvent();

        outputOptionPane = new Pane(new Region());
        artnetOptions = new ArtNetConfigViewer(m);
        artnetOptions.getChangeEvent().isChanged().addListener(cl -> {
            changeEventHandler.setEvent(artnetOptions.getChangeEvent());
        });
        oscOptions = new OSCConfigViewer(m);
        oscOptions.getChangeEvent().isChanged().addListener(cl -> {
            changeEventHandler.setEvent(oscOptions.getChangeEvent());
        });
        outputOptionChanged = new SimpleStringProperty();

        options = FXCollections.observableArrayList(OutputTypes.getOutputTypes());
        comboBox = new ComboBox(options);
        comboBox.setValue(m.getOutputType().getName());
        comboBox.setOnAction(eh -> comboBoxChanged());
        comboBoxChanged();

        startStopButton = new Button("Start");
        startStopButton.setOnAction(eh -> {
            changeEventHandler.setEvent(ConfigPropertyTypes.START_STOP_MODULE);
        });
        
        ts = new ToggleSwitch();

        getChildren().add(comboBox);
        getChildren().add(outputOptionPane);
        getChildren().add(startStopButton);
        getChildren().add(ts);

    }

    private void comboBoxChanged() {

        outputOptionChanged.set(comboBox.getValue().toString());

        if (comboBox.getValue().equals(OutputTypes.NONE.getName())) {
            outputOptionPane.getChildren().remove(0);
            outputOptionPane.getChildren().add(new Region());
        } else if (comboBox.getValue().equals(OutputTypes.ARTNET.getName())) {
            outputOptionPane.getChildren().remove(0);
            outputOptionPane.getChildren().add(artnetOptions);
        } else if (comboBox.getValue().equals(OutputTypes.OSC.getName())) {
            outputOptionPane.getChildren().remove(0);
            outputOptionPane.getChildren().add(oscOptions);
        }

    }

    //Current Output Option Listener
    public SimpleStringProperty getOutputOption() {
        return outputOptionChanged;
    }

    public ConfigChangeEvent getChangeEvent() {
        return changeEventHandler;
    }
}

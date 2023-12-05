/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edisongrauman.lightcontrol;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Edison Grauman
 */
class OSCConfigViewer extends TitledPane {

    private VBox frame;

    private TextFieldLabel sourcePortField;
    private TextFieldLabel destPortField;

    private ConfigChangeEvent changeEventHandler;

    public OSCConfigViewer(Module m) {
        changeEventHandler = new ConfigChangeEvent();
        
//        ipField = new TextFieldLabel("IP Address", "", ValidatorTypes.IP);
        destPortField = new TextFieldLabel("Destination Port", m.getOscDestinationPort(), ValidatorTypes.NUM_RANGE, 0, 9999);
        destPortField.getlastValidatedString().addListener(cl -> {
            changeEventHandler.setEvent(ConfigPropertyTypes.OSC_DESTINATION_PORT, destPortField.getlastValidatedString().getValue());

        });
        sourcePortField = new TextFieldLabel("Source Port", m.getOscSourcePort(), ValidatorTypes.NUM_RANGE, 0, 9999);
        sourcePortField.getlastValidatedString().addListener(cl -> {
            changeEventHandler.setEvent(ConfigPropertyTypes.OSC_SOURCE_PORT, sourcePortField.getlastValidatedString().getValue());
        });

        frame = new VBox();
        frame.getChildren().add(sourcePortField);
        frame.getChildren().add(destPortField);

        this.setText("OSC Options");
        this.setContent(frame);

    }

    public ConfigChangeEvent getChangeEvent() {
        return changeEventHandler;
    }

}

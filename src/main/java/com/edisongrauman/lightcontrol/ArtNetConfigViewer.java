/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edisongrauman.lightcontrol;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Edison Grauman
 */
public class ArtNetConfigViewer extends TitledPane {

    private VBox frame;

    private TextFieldLabel ipField;
    private TextFieldLabel sourcePortField;
    private TextFieldLabel destPortField;

    private ConfigChangeEvent changeEventHandler;

    public ArtNetConfigViewer(Module m) {
        changeEventHandler = new ConfigChangeEvent();

        ipField = new TextFieldLabel("IP Address", m.getArtnetIPAddress(), ValidatorTypes.IP);
        ipField.getlastValidatedString().addListener(cl -> {
            changeEventHandler.setEvent(ConfigPropertyTypes.ARTNET_IP_ADDRESS, ipField.getlastValidatedString().getValue());
        });
        destPortField = new TextFieldLabel("Destination Port", m.getArtnetDestinationPort(), ValidatorTypes.NUM_RANGE, 0, 9999);
        destPortField.getlastValidatedString().addListener(cl -> {
            changeEventHandler.setEvent(ConfigPropertyTypes.ARTNET_DESTINATION_PORT, destPortField.getlastValidatedString().getValue());
        });
        sourcePortField = new TextFieldLabel("Source Port", m.getArtnetSourcePort(), ValidatorTypes.NUM_RANGE, 0, 9999);
        sourcePortField.getlastValidatedString().addListener(cl -> {
            changeEventHandler.setEvent(ConfigPropertyTypes.ARTNET_SOURCE_PORT, sourcePortField.getlastValidatedString().getValue());
        });

        frame = new VBox();
        frame.getChildren().add(ipField);
        frame.getChildren().add(sourcePortField);
        frame.getChildren().add(destPortField);

        this.setText("Artnet Options");
        this.setContent(frame);

    }
    public ConfigChangeEvent getChangeEvent() {
        return changeEventHandler;
    }
}

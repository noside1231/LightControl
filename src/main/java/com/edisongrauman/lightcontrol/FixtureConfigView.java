/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edisongrauman.lightcontrol;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Accordion;
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
public class FixtureConfigView extends TitledPane {

    private VBox frame = new VBox();

    private Button deleteFixture;
    private SimpleBooleanProperty deleteButtonPressed;

    private TextFieldLabel name;
    private TextFieldLabel universe;
    private TextFieldLabel channels;
    private TextFieldLabel startFromChannel;

    public FixtureConfigView(Fixture fixture) {

        name = new TextFieldLabel("Fixture Name", "Fixture");
        universe = new TextFieldLabel("Universe", "1", ValidatorTypes.NUM_RANGE, 1, 100);
        channels = new TextFieldLabel("Channels", "1", ValidatorTypes.NUM_RANGE, 1, 100);
        startFromChannel = new TextFieldLabel("Start Channel", "1", ValidatorTypes.NUM_RANGE, 1, 100);

        deleteButtonPressed = new SimpleBooleanProperty(false);
        deleteFixture = new Button("Delete");
        deleteFixture.setOnAction(eh -> {
            System.out.println("Delete button pressed in fixture config view");
            deleteButtonPressed.setValue(!deleteButtonPressed.getValue());
        });

        frame.getChildren().add(name);
        frame.getChildren().add(universe);
        frame.getChildren().add(channels);
        frame.getChildren().add(startFromChannel);
        frame.getChildren().add(deleteFixture);
        this.setContent(frame);

        this.textProperty().bind(name.getlastValidatedString());

    }
    
    public SimpleBooleanProperty getFixtureDeleteButtonPressed() {
        return deleteButtonPressed;
    }

    public SimpleStringProperty getFixtureName() {
        return name.getlastValidatedString();
    }

    public SimpleStringProperty getFixtureUniverse() {
        return universe.getlastValidatedString();
    }

    public SimpleStringProperty getFixtureChannelCount() {
        return channels.getlastValidatedString();
    }

    public SimpleStringProperty getFixtureChannelStart() {
        return startFromChannel.getlastValidatedString();
    }
    
    public void setFixtureEdit(boolean b) {
//        name.setDisable(!b);
//        universe.setDisable(!b);
//        channels.setDisable(!b);
//        startFromChannel.setDisable(!b);
        frame.setDisable(!b);
    }
}

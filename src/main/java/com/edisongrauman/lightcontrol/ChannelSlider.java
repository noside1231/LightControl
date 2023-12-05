/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edisongrauman.lightcontrol;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.NumberStringConverter;

/**
 *
 * @author Edison Grauman
 */
public class ChannelSlider extends VBox {

    private Slider slider;
    private ValidatedTextField textField;
    private Label label;

    private SimpleIntegerProperty value;

    public ChannelSlider() {
        value = new SimpleIntegerProperty(0);

        label = new Label("Channel");

        slider = new Slider(0, 255, 0);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(51);
        slider.setMinorTickCount(0);
        slider.setBlockIncrement(1);
        slider.orientationProperty().set(Orientation.VERTICAL);
        slider.valueProperty().addListener(cl -> {
            setValue((int)Math.round(slider.getValue()));
        });

        textField = new ValidatedTextField("0", ValidatorTypes.NUM_RANGE, 0, 255);
        textField.getlastValidatedString().addListener(cl -> {
            setValue(Integer.parseInt(textField.getlastValidatedString().get()));
        });
        textField.setMaxWidth(50);

        this.getChildren().addAll(label, slider, textField);
        this.setAlignment(Pos.CENTER);

        value.addListener(cl -> System.out.println("Value: " + value.getValue()));
        
        setValue(0);

    }
    
    public void setValue(int val) {
        textField.setValue(Integer.toString(val));
        value.setValue(val);
        slider.setValue(val);
    }

    public SimpleIntegerProperty getValue() {
        return value;
    }

}

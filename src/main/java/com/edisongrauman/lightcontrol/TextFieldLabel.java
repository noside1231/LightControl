/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edisongrauman.lightcontrol;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.apache.commons.validator.routines.IntegerValidator;

/**
 *
 * @author Edison Grauman
 */
public class TextFieldLabel extends HBox {

    private final Label label;
    private TextField textField;

    private SimpleStringProperty lastValidatedString;

    private final ValidatorTypes validatorType;
    private final int min;
    private final int max;

    public TextFieldLabel(String name, String value) {
        this(name, value, ValidatorTypes.NONE);
    }

    public TextFieldLabel(String name, String value, ValidatorTypes validatorType) {
        this(name, value, validatorType, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public TextFieldLabel(String name, String value, ValidatorTypes validatorType, int min, int max) {
        this.min = min;
        this.max = max;
        this.validatorType = validatorType;
        label = new Label(name);
        textField = new TextField(value);
        textField.focusedProperty().addListener(cl -> {
            if (validateEntry(textField.getText())) {
                lastValidatedString.set(textField.getText());
            } else {
                textField.setText(lastValidatedString.get());
            }
        });

        lastValidatedString = new SimpleStringProperty(value);

        getChildren().addAll(label, textField);
    }

    private boolean validateEntry(String str) {
        switch (validatorType) {
            case NONE:
                return true;
            case IP:
                InetAddressValidator ipValidator = InetAddressValidator.getInstance();
                try {
                    return ipValidator.isValid(str);
                } catch (Exception e) {
                }
                return false;
            case NUM_RANGE:
                IntegerValidator rangeValidator = IntegerValidator.getInstance();
                try {
                    int i = rangeValidator.validate(str);
                    return rangeValidator.isInRange(i, min, max);
                } catch (Exception e) {
                }
                return false;

            default:
                return false;
        }
    }

    public SimpleStringProperty getlastValidatedString() {
        return lastValidatedString;
    }

    public void setText(String text) {
        textField.setText(text);
    }

}

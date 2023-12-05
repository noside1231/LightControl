/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edisongrauman.lightcontrol;

import static com.edisongrauman.lightcontrol.ValidatorTypes.IP;
import static com.edisongrauman.lightcontrol.ValidatorTypes.NONE;
import static com.edisongrauman.lightcontrol.ValidatorTypes.NUM_RANGE;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.apache.commons.validator.routines.IntegerValidator;

/**
 *
 * @author Edison Grauman
 */
public class ValidatedTextField extends TextField{

//    private TextField textField;

    private SimpleStringProperty lastValidatedString;

    private final ValidatorTypes validatorType;
    private final int min;
    private final int max;

    public ValidatedTextField(String value, ValidatorTypes validatorType, int min, int max) {
        this.min = min;
        this.max = max;
        this.validatorType = validatorType;

//        textField = new TextField(value);
        this.focusedProperty().addListener(cl -> {
            if (validateEntry(this.getText())) {
                lastValidatedString.set(this.getText());
            } else {
                this.setText(lastValidatedString.get());
            }
        });
        lastValidatedString = new SimpleStringProperty(value);

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

    public void setValue(String text) {
        this.setText(text);
    }

}

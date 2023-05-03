package com.example.if2210_tb2_nge.components;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import lombok.Getter;

public class TextFieldForm extends Control {
    @Getter
    private VBox formContainer;
    private Label label;
    private TextField inputField;

    public TextFieldForm(String labelStr, String placeholderStr, Integer width) {
        // form container
        formContainer = new VBox();

        // label
        label = new Label(labelStr);
        formContainer.getChildren().add(label);

        // input field
        inputField = new TextField();
        inputField.setPromptText(placeholderStr);
        inputField.setPrefHeight(30);
        inputField.setPrefWidth(width);
        formContainer.getChildren().add(inputField);
    }

    public String getValue() {
        return inputField.getText();
    }

    public void setValue(String value) {inputField.setText(value);}
}

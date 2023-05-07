package com.example.if2210_tb2_nge.components;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import lombok.Getter;

public class TextFieldForm extends Control {
    @Getter
    private VBox formContainer;
    private Label label;
    @Getter
    private TextField inputField;

    public TextFieldForm(String labelStr, String placeholderStr, Integer width) {
        // form container
        formContainer = new VBox();

        // label
        label = new Label(labelStr);
        Font labelFont = Font.loadFont("file:src/assets/Montserrat-Regular.ttf", 20);
        label.setFont(labelFont);
        VBox.setMargin(label, new javafx.geometry.Insets(20, 0, 10, 0));
        formContainer.getChildren().add(label);

        // input field
        inputField = new TextField();
        Font fieldFont = Font.loadFont("file:src/assets/Montserrat-Regular.ttf", 15);
        inputField.setPromptText(placeholderStr);
        inputField.setPrefHeight(50);
        inputField.setPrefWidth(width);
        inputField.setFont(fieldFont);
        inputField.setStyle("-fx-text-fill: black; -fx-font-size: 20; -fx-background-color: #E1E1E1;");
        formContainer.getChildren().add(inputField);
    }

    public String getValue() {
        return inputField.getText();
    }

    public void setValue(String value) {inputField.setText(value);}

    public void setIsDisable(Boolean isDisable) {
        if (isDisable) {
            inputField.setDisable(true);
        } else {
            inputField.setDisable(false);
        }
    }
}

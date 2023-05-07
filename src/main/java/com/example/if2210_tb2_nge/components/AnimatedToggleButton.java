package com.example.if2210_tb2_nge.components;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.File;
import java.net.MalformedURLException;

public class AnimatedToggleButton extends StackPane {

    private ToggleButton button;
    private Rectangle buttonBackground;
    private Circle backgroundCircle;
    private ScaleTransition scaleTransition;
    private TranslateTransition translateTransition;

    public AnimatedToggleButton() {
        button = new ToggleButton();
        button.getStyleClass().add("animated-toggle-button");

        buttonBackground = new Rectangle(40, 20);
        buttonBackground.setFill(Color.GRAY);
        buttonBackground.setStroke(Color.BLACK);
        buttonBackground.setArcHeight(20);
        buttonBackground.setArcWidth(20);

        backgroundCircle = new Circle(8);
        backgroundCircle.setFill(Color.GRAY);
        backgroundCircle.setTranslateX(-10);

        getChildren().addAll(buttonBackground, backgroundCircle, button);

        scaleTransition = new ScaleTransition(Duration.seconds(0.2), backgroundCircle);
        scaleTransition.setToX(1.5);
        scaleTransition.setToY(1.5);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(2);



        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (button.isSelected()) {
                    scaleTransition.setRate(1);
                    scaleTransition.play();
                    backgroundCircle.setFill(Color.web("#478660"));
                    translateTransition = new TranslateTransition(Duration.seconds(0.2), backgroundCircle);
                    translateTransition.setFromX(-10);
                    translateTransition.setToX(10);
                    translateTransition.play();
                } else {
                    scaleTransition.setRate(-1);
                    scaleTransition.play();
                    backgroundCircle.setFill(Color.GRAY);
                    translateTransition = new TranslateTransition(Duration.seconds(0.2), backgroundCircle);
                    translateTransition.setFromX(10);
                    translateTransition.setToX(-10);
                    translateTransition.play();
                }
            }
        });

        button.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                buttonBackground.setFill(Color.LIGHTGRAY);
            }
        });

        button.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (button.isSelected()) {
                    buttonBackground.setFill(Color.web("#478660"));
                } else {
                    buttonBackground.setFill(Color.GRAY);
                }
            }
        });
    }
}
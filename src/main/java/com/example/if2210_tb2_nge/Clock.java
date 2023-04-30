package com.example.if2210_tb2_nge;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.*;

public class Clock {

    private Label clockLabel;
    private Timeline clock;
    public Clock(){
        clockLabel = new Label();
        this.clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            clockLabel.setText(currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        }), new KeyFrame(Duration.seconds(1)));
        clockLabel.setFont(new Font(60));
    }

    public Label getlabel(){
        return clockLabel;
    }

    public void setClock(){
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
}

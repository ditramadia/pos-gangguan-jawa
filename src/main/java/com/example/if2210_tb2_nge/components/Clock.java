package com.example.if2210_tb2_nge.components;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.*;
import lombok.Getter;
import lombok.Setter;


public class Clock {
    @Getter
    @Setter
    private Label clockLabel;
    private Timeline clock;
    private Thread clockThread;

    public Clock(){
        clockLabel = new Label();
        clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            clockLabel.setText(currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        }), new KeyFrame(Duration.seconds(1)));
        clockLabel.setFont(new Font(60));
    }

    public void setClock(){
        clockThread = new Thread(() -> {
            clock.setCycleCount(Animation.INDEFINITE);
            clock.play();
        });
        clockThread.setDaemon(true);
        clockThread.start();
    }
}

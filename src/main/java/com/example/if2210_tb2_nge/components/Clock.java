package com.example.if2210_tb2_nge.components;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javafx.scene.control.*;
import lombok.Getter;
import lombok.Setter;


public class Clock {
    @Getter
    private Label clockLabelH;
    @Getter
    private Label clockLabelM;
    @Getter
    private Label date;
    private Timeline clock;
    private Thread clockThread;
    private volatile boolean stop = true;

    public Clock() {
        date = new Label();
        date.setStyle("-fx-font-size: 24; -fx-font-weight: bold");

        // Set the current date to the Label
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy");
        date.setText(currentDate.format(formatter));
        clockLabelH = new Label();
        clockLabelM = new Label();
        clockLabelH.setFont(new Font(80));
        clockLabelM.setFont(new Font(80));
        clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime time = LocalTime.now();
            String formattedHour = time.format(DateTimeFormatter.ofPattern("hh"));
            String formattedMinute = time.format(DateTimeFormatter.ofPattern("mm"));
            clockLabelH.setText(formattedHour);
            clockLabelM.setText(formattedMinute);
        }), new KeyFrame(Duration.seconds(1)));

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

package com.example.if2210_tb2_nge.pages;

import com.example.if2210_tb2_nge.components.Clock;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import lombok.Getter;

public class HomePage {
    @Getter
    private Tab tab;
    private VBox container;
    @Getter
    private VBox sideMenu;
    @Getter
    private Button button1;
    @Getter
    private Button button2;
    @Getter
    private Button button3;
    private BorderPane layout;
    private Clock clock;

    public HomePage(){
        // layout
        tab = new Tab("Home");
        layout = new BorderPane();
        container = new VBox();
        container.setAlignment(Pos.TOP_CENTER);

        // side bar
        sideMenu = new VBox();
        button1 = new Button("Button 1");
        button2 = new Button("Button 2");
        button3 = new Button("Button 3");
        sideMenu.getChildren().addAll(button1,button2,button3);
        sideMenu.setStyle("-fx-background-color: #ADD8E6;");
        sideMenu.setPrefWidth(200);

        // clock
        clock = new Clock();
        VBox.setMargin(clock.getClockLabel(), new Insets(100,0,0,0));
        clock.setClock();

        // author name and nim
        Label kel1 = new Label("Kelvin Rayhan A.       13521005");
        Label kel2 = new Label("Ditra R. Amadia         13521019");
        Label kel3 = new Label("Varraz Hazzandra A.   13521020");
        Label kel4 = new Label("Bernardus Willson     13521021");
        Label kel5 = new Label("Raditya Naufal A.     13521022");
        Label kel6 = new Label("Kenny Benaya N,       13521023");
        Label kel = new Label("Gangguan Jawa");
        VBox.setMargin(kel,new Insets(80, 10 ,0 ,10));
        VBox.setMargin(kel1,new Insets(50, 10 ,0 ,10));
        kel.setFont(new Font(40));

        // add component to layout
        container.getChildren().add(clock.getClockLabel());
        container.getChildren().add(kel);
        container.getChildren().addAll(kel1,kel2,kel3,kel4,kel5);
        layout.setCenter(container);
        layout.setLeft(sideMenu);
        tab.setContent(layout);
    }

}

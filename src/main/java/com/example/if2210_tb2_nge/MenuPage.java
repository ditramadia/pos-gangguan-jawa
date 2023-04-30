package com.example.if2210_tb2_nge;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MenuPage {
    private VBox layout;
    private Clock clock;

    public MenuPage(){
        layout = new VBox();
        layout.setAlignment(Pos.TOP_CENTER);

        clock = new Clock();
        VBox.setMargin(clock.getlabel(), new Insets(30,0,0,0));
        clock.setClock();

        Label kel = new Label("NTOT");
        VBox.setMargin(kel,new Insets(100, 10 ,0 ,10));
        kel.setFont(new Font(70));

        layout.getChildren().add(kel);
        layout.getChildren().add(clock.getlabel());
    }

    public VBox getlayout(){
        return layout;
    }
}

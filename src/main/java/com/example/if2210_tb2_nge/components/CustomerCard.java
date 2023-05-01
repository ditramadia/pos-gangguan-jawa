package com.example.if2210_tb2_nge.components;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import lombok.Getter;

import java.io.File;
import java.net.MalformedURLException;

public class CustomerCard {
    @Getter
    private HBox cardContainer;
    @Getter
    private Label customerName;
    @Getter
    private Button viewDetailBtn;

    public CustomerCard() throws MalformedURLException {
        cardContainer = new HBox();
        cardContainer.setStyle("-fx-background-color: #D7CDC7; -fx-background-radius: 20");
        cardContainer.setPrefWidth(800);
        cardContainer.setPrefHeight(50);

        customerName = new Label();
        customerName.setFont(new Font(18));
        HBox.setMargin(customerName, new Insets(20,0,10,10));

        viewDetailBtn = new Button("Lihat Detail");
        HBox.setMargin(viewDetailBtn, new Insets(10,0,10,0));

        File cssFile = new File("src/main/java/com/example/if2210_tb2_nge/style/buttonstyle.css");
        String cssUrl = cssFile.toURI().toURL().toExternalForm();
        viewDetailBtn.getStylesheets().add(cssUrl);

        cardContainer.getChildren().addAll(customerName,viewDetailBtn);
    }
}

package com.example.if2210_tb2_nge.pages;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import lombok.Getter;

public class CheckoutPage extends BorderPane {
    private VBox contentContainer;
    private ImageView image;
    private Label title;
    @Getter
    private Button registration;
    private Button backBtn;

    public CheckoutPage() {
        contentContainer = new VBox();
        image = new ImageView(new Image("File:src/assets/check.jpeg"));
        title = new Label("Purchase Successful");
        registration = new Button("Become A Member");
        backBtn = new Button("Back");
        contentContainer.getChildren().addAll(image,title,registration,backBtn);
        setCenter(contentContainer);
    }

}

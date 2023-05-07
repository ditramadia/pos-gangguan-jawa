package com.example.if2210_tb2_nge.pages;

import com.example.if2210_tb2_nge.controller.CustomerController;
import com.example.if2210_tb2_nge.entity.Members;
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
    @Getter
    private Button backBtn;

    public CheckoutPage() {
        // Content container
        contentContainer = new VBox();
        setCenter(contentContainer);

        // Check Image
        image = new ImageView(new Image("File:src/assets/check.jpeg"));
        contentContainer.getChildren().add(image);

        // Title
        title = new Label("Purchase Successful");
        contentContainer.getChildren().add(title);

        // Registration button
        registration = new Button("Become A Member");
        contentContainer.getChildren().add(registration);

        // Back button
        backBtn = new Button("Back");
        contentContainer.getChildren().add(backBtn);
    }

    public void reloadPage() {
        // Content container
        contentContainer.getChildren().clear();

        // Check Image
        image = new ImageView(new Image("File:src/assets/check.jpeg"));
        contentContainer.getChildren().add(image);

        // Title
        title = new Label("Purchase Successful");
        contentContainer.getChildren().add(title);

        // Registration button
        registration = new Button("Become A Member");
        if (CustomerController.getCustomerInstance() == null || CustomerController.getCustomerInstance().getName() == "") {
            contentContainer.getChildren().add(registration);
        }

        // Back button
        backBtn = new Button("Back");
        contentContainer.getChildren().add(backBtn);
    }
}

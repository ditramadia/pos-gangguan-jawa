package com.example.if2210_tb2_nge.pages;

import com.example.if2210_tb2_nge.controller.CustomerController;
import com.example.if2210_tb2_nge.entity.Members;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import lombok.Getter;

public class CheckoutPage extends BorderPane {
    private VBox contentContainer;
    private ImageView image;
    private Label title;
    private Label desc;
    @Getter
    private Button registration;
    @Getter
    private Button backBtn;

    public CheckoutPage() {
        // Content container
        contentContainer = new VBox();
        contentContainer.setAlignment(Pos.CENTER);
        setCenter(contentContainer);

        // Check Image
        image = new ImageView(new Image("File:src/assets/check.jpeg"));
        contentContainer.getChildren().add(image);

        // Title
        title = new Label("THANK YOU FOR YOUR PURCHASE");
        Font fontTitle = Font.loadFont("File:src/assets/Montserrat-Bold.ttf", 50);
        title.setFont(fontTitle);
        title.setStyle("-fx-text-fill: #8C7466;");
        contentContainer.getChildren().add(title);

        // Description
        desc = new Label("You will be receiving a receipt with your order details");
        contentContainer.getChildren().add(desc);

        // Registration button
        registration = new Button("BECOME A MEMBER");
        contentContainer.getChildren().add(registration);

        // Back button
        backBtn = new Button("BACK TO HOMEPAGE");
        contentContainer.getChildren().add(backBtn);
    }

    public void reloadPage() {
        // Content container
        contentContainer.getChildren().clear();

        // Check Image
        image = new ImageView(new Image("File:src/assets/check.jpeg"));
        contentContainer.getChildren().add(image);

        // Title
        title = new Label("THANK YOU FOR YOUR PURCHASE");
        VBox.setMargin(title, new Insets(50, 0, 20, 0));
        Font fontTitle = Font.loadFont("File:src/assets/Montserrat-Bold.ttf", 50);
        title.setFont(fontTitle);
        title.setStyle("-fx-text-fill: #8C7466;");
        contentContainer.getChildren().add(title);

        // Description
        desc = new Label("You will be receiving a receipt with your order details");
        VBox.setMargin(desc, new Insets(0, 0, 50, 0));
        Font fontDesc = Font.loadFont("File:src/assets/Montserrat-Regular.ttf", 20);
        desc.setFont(fontDesc);
        desc.setStyle("-fx-text-fill: #8C7466;");
        contentContainer.getChildren().add(desc);

        // Registration button
        registration = new Button("BECOME A MEMBER");
        registration.setStyle("-fx-background-color: #478660; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");
        registration.setPrefWidth(400);
        if (CustomerController.getCustomerInstance() == null || CustomerController.getCustomerInstance().getName() == "") {
            contentContainer.getChildren().add(registration);
        }

        // Back button
        backBtn = new Button("BACK TO HOMEPAGE");
        backBtn.setStyle("-fx-background-color: #8C7466; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");
        backBtn.setPrefWidth(400);
        VBox.setMargin(backBtn, new Insets(20, 0, 0, 0));
        contentContainer.getChildren().add(backBtn);
    }
}

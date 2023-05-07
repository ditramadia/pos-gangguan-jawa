package com.example.if2210_tb2_nge.components;

import com.example.if2210_tb2_nge.entity.Members;
import com.example.if2210_tb2_nge.repository.CustomersRepository;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import lombok.Getter;

import java.net.MalformedURLException;
import java.util.List;

public class CustomerCard {
    @Getter
    private HBox cardContainer;
    @Getter
    private HBox customerContainer;
    @Getter
    private Label customerName;
    @Getter
    private Button viewDetailBtn;

    public CustomerCard(double i) throws MalformedURLException {
        customerContainer = new HBox();
        customerContainer.setAlignment(Pos.CENTER);
        customerContainer.setSpacing(15);

        cardContainer = new HBox();
        cardContainer.setStyle("-fx-background-color: #D7CDC7; -fx-background-radius: 5");
        cardContainer.setPrefWidth(1400);
        cardContainer.setPrefHeight(50);

        Font fontName = Font.loadFont("file:src/assets/Montserrat-Regular.ttf", 25);

        List<Members> customers = CustomersRepository.getMembersOnly();
        for (Members customer : customers) {
            Integer id = customer.getId();
            if (id == i) {
                // item name
                customerName = new Label(customer.getName());
                cardContainer.getChildren().add(customerName);
                customerName.setFont(fontName);
                customerName.setStyle("-fx-text-fill: #8C7466;");
                cardContainer.setAlignment(Pos.CENTER_LEFT);
                HBox.setMargin(customerName, new Insets(0, 0, 0, 30));
            }
        }

        viewDetailBtn = new Button("View Profile");
        viewDetailBtn.setPrefWidth(200);
        viewDetailBtn.setPrefHeight(50);
        viewDetailBtn.setStyle("-fx-background-color: #D7CDC7; -fx-background-radius: 5; -fx-text-fill: #8C7466;");
        viewDetailBtn.setFont(fontName);

        customerContainer.getChildren().add(cardContainer);
        customerContainer.getChildren().add(viewDetailBtn);
    }
}

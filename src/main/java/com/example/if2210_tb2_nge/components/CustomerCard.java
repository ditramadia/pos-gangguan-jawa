package com.example.if2210_tb2_nge.components;

import com.example.if2210_tb2_nge.entity.Customers;
import com.example.if2210_tb2_nge.repository.CustomersRepository;
import com.example.if2210_tb2_nge.repository.ItemsRepository;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import lombok.Getter;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

public class CustomerCard {
    @Getter
    private HBox cardContainer;
    @Getter
    private Label customerName;
    @Getter
    private Button viewDetailBtn;

    public CustomerCard(double i) throws MalformedURLException {
        cardContainer = new HBox();
        cardContainer.setStyle("-fx-background-color: #D7CDC7; -fx-background-radius: 20");
        cardContainer.setPrefWidth(800);
        cardContainer.setPrefHeight(50);


        List<Customers> customers = CustomersRepository.getCustomers();
        for (Customers customer : customers) {
            Integer id = customer.getId();
            if (id == i) {
                // item name
                customerName = new Label(customer.getName());
                cardContainer.getChildren().add(customerName);
                customerName.setFont(new Font("Arial", 20));
                cardContainer.setAlignment(Pos.TOP_CENTER);
                HBox.setMargin(customerName, new Insets(20, 0, 10, 10));
            }

        }
        viewDetailBtn = new Button("Lihat Detail");
        HBox.setMargin(viewDetailBtn, new Insets(10, 0, 10, 0));


        cardContainer.getChildren().add(viewDetailBtn);
    }
}

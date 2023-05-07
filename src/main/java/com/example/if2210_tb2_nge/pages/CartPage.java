package com.example.if2210_tb2_nge.pages;

import com.example.if2210_tb2_nge.components.CustomerSelectionCard;
import com.example.if2210_tb2_nge.controller.ItemController;
import com.example.if2210_tb2_nge.entity.CartItem;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartPage {
    @Getter
    private BorderPane pageContainer;
    private ScrollPane scrollPane;
    private HBox cartLayout;
    private VBox customerPrice;
    private CustomerSelectionCard customerLayout;
    private VBox priceLayout;
    private Label title;
    private VBox contentContainer;
    private HBox itemContainer;
    private Label itemName;
    private Label itemPrice;
    private Label quantity;
    @Getter
    private Button backBtn;

    public CartPage(List<CartItem> cartItems) {
        pageContainer = new BorderPane();
        scrollPane = new ScrollPane();
        contentContainer = new VBox();
        cartLayout = new HBox();
        customerPrice = new VBox();
        customerLayout = new CustomerSelectionCard();
        priceLayout = new VBox();
        title = new Label("CART");
        title.setFont(new Font(30));
        backBtn = new Button("Back");

        cartLayout.getChildren().addAll(scrollPane, customerPrice);

        scrollPane.setContent(contentContainer);

        customerPrice.getChildren().addAll(customerLayout.getCardContainer(), priceLayout);

        pageContainer.setTop(title);
        pageContainer.setCenter(cartLayout);
        pageContainer.setBottom(backBtn);
        for (CartItem item : cartItems){
            if (item.getQuantity() != 0) {
                HBox newitemContainer = new HBox();
                newitemContainer.setStyle("-fx-background-color: #D7CDC7; -fx-background-radius: 20");
                newitemContainer.setPrefWidth(800);
                newitemContainer.setPrefHeight(50);
                Label newitemName = new Label(item.getItem().getName());
                Label newitemPrice = new Label(Integer.toString(item.getItem().getPrice()));
                Label newquantity = new Label(Integer.toString(item.getQuantity()));

                newitemContainer.getChildren().addAll(newitemName, newquantity, newitemPrice);
                VBox.setMargin(newitemContainer, new Insets(30));
                contentContainer.getChildren().add(newitemContainer);
            }
        }


    }

    public void setCart (List<CartItem> cartItems){
        contentContainer = new VBox();
        for (CartItem item : cartItems){
            if (item.getQuantity() != 0) {
                ItemController.setItemInstance(item.getItem().getId());
                HBox newitemContainer = new HBox();
                newitemContainer.setStyle("-fx-background-color: #D7CDC7; -fx-background-radius: 20");
                newitemContainer.setPrefWidth(800);
                newitemContainer.setPrefHeight(50);
                Label newitemName = new Label(item.getItem().getName());
                Label newitemPrice = new Label(Integer.toString(item.getItem().getPrice()));
                Label newquantity = new Label(Integer.toString(item.getQuantity()));

                newitemContainer.getChildren().addAll(newitemName, newquantity, newitemPrice);
                VBox.setMargin(newitemContainer, new Insets(30));
                contentContainer.getChildren().add(newitemContainer);
            }
        }
        scrollPane.setContent(contentContainer);
    }
}

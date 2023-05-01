package com.example.if2210_tb2_nge.pages;

import com.example.if2210_tb2_nge.components.CustomerCard;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import lombok.Getter;

import java.net.MalformedURLException;

public class CustomerPage {
    @Getter
    private Tab tab;
    private StackPane mulscreens;
    private BorderPane pageConteiner;

    private ScrollPane scrollPane;
    private VBox cardContent;
    private Label title;


    public CustomerPage() throws MalformedURLException {
        tab = new Tab("Customer");
        mulscreens = new StackPane();
        pageConteiner = new BorderPane();
        scrollPane = new ScrollPane();
        cardContent = new VBox();

        title = new Label("MEMBER");
        title.setFont(new Font("Arial", 20));
        title.setPrefHeight(100);
        title.setPrefWidth(300);
        pageConteiner.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);
        cardContent.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: #83695A;");

        for (int i = 0; i < 50; i++){
            CustomerCard newCustomerCard = new CustomerCard();
            cardContent.getChildren().add(newCustomerCard.getCardContainer());
            newCustomerCard.getViewDetailBtn().setOnAction(e -> {
                mulscreens.getChildren().get(0).setVisible(false);
                mulscreens.getChildren().get(1).setVisible(true);
            });
            ;
        }
        BorderPane.setMargin(scrollPane, new Insets(10,0,0, 300));
        BorderPane.setAlignment(scrollPane, Pos.CENTER);
        scrollPane.setContent(cardContent);
        pageConteiner.setCenter(scrollPane);




        CustomerDetailPage customerDetailPage = new CustomerDetailPage();
        mulscreens.getChildren().add(pageConteiner);
        mulscreens.getChildren().add(customerDetailPage.getPageContainer());
        mulscreens.getChildren().get(1).setVisible(false);

        tab.setContent(mulscreens);
    }

}

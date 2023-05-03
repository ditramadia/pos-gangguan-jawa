package com.example.if2210_tb2_nge.pages;

import com.example.if2210_tb2_nge.components.CustomerCard;
import com.example.if2210_tb2_nge.components.ItemCard;
import com.example.if2210_tb2_nge.controller.ItemController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import lombok.Getter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.MalformedURLException;

public class CustomerPage {
    @Getter
    private Tab tab;
    private StackPane mulscreens;
    private BorderPane pageConteiner;
    private CustomerDetailPage customerDetailPage;

    private ScrollPane scrollPane;
    private VBox cardContent;
    private Label title;


    public CustomerPage() throws Exception {
        tab = new Tab("Customer");
        tab.setStyle("-fx-background-radius: 10 10 0 0;");
        TabPane tabPane = new TabPane();
        tabPane.getTabs().add(tab);

        mulscreens = new StackPane();
        pageConteiner = new BorderPane();
        scrollPane = new ScrollPane();
        cardContent = new VBox();

        customerDetailPage = new CustomerDetailPage();
        //hide
        mulscreens.getChildren().add(pageConteiner);
        mulscreens.getChildren().add(customerDetailPage.getPageContainer());
        mulscreens.getChildren().get(1).setVisible(false);

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

        // add card
        this.updateCard();

//        for (int i = 0; i < 10; i++){
//            CustomerCard newCustomerCard = new CustomerCard();
//            cardContent.getChildren().add(newCustomerCard.getCardContainer());
//            newCustomerCard.getViewDetailBtn().setOnAction(e -> {
//                mulscreens.getChildren().get(0).setVisible(false);
//                mulscreens.getChildren().get(1).setVisible(true);
//            });
//            ;
//        }
        BorderPane.setMargin(scrollPane, new Insets(10,0,0, 300));
        BorderPane.setAlignment(scrollPane, Pos.CENTER);
        scrollPane.setContent(cardContent);
        pageConteiner.setCenter(scrollPane);





//        mulscreens.getChildren().add(pageConteiner);
//        mulscreens.getChildren().add(customerDetailPage.getPageContainer());
//        mulscreens.getChildren().get(1).setVisible(false);

        tab.setContent(mulscreens);
    }

    public void updateCard() throws Exception {
        JSONObject jsonObj = ItemController.readItemsJSON("src/main/java/com/example/if2210_tb2_nge/database/Customers.json");
        JSONArray itemsArray = (JSONArray) jsonObj.get("customers");
        for (Object itemsObj : itemsArray) {
            JSONObject product = (JSONObject) itemsObj;
            if (product.get("name") == null) {
                continue;
            }
            Long id = (Long) product.get("id");
            CustomerCard newCustomerCard = new CustomerCard();
            cardContent.getChildren().add(newCustomerCard.getCardContainer());
            newCustomerCard.getCustomerName().setText((String) product.get("name"));

            newCustomerCard.getViewDetailBtn().setOnAction(e -> {
                try {
                    customerDetailPage.loadData(id.intValue());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                mulscreens.getChildren().get(0).setVisible(false);
                mulscreens.getChildren().get(1).setVisible(true);
            });

//            GridPane.setMargin(newCustomerCard.getCardContainer(), new Insets(30));
//            cardLayout.add(newCustomerCard.getCardContainer(), column++, row);
        }
    }

}

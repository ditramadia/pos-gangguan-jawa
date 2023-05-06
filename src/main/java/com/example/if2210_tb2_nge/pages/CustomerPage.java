package com.example.if2210_tb2_nge.pages;

import com.example.if2210_tb2_nge.components.CustomerCard;
import com.example.if2210_tb2_nge.components.ItemCard;
import com.example.if2210_tb2_nge.controller.ItemController;
import com.example.if2210_tb2_nge.entity.Customers;
import com.example.if2210_tb2_nge.repository.CustomersRepository;
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
import java.util.List;
import java.util.Map;

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


        BorderPane.setMargin(scrollPane, new Insets(10,0,0, 300));
        BorderPane.setAlignment(scrollPane, Pos.CENTER);
        pageConteiner.setCenter(scrollPane);





//        mulscreens.getChildren().add(pageConteiner);
//        mulscreens.getChildren().add(customerDetailPage.getPageContainer());
//        mulscreens.getChildren().get(1).setVisible(false);

        tab.setContent(mulscreens);
    }

    public void updateCard() throws Exception {
      VBox newCardLayout = new VBox();
      newCardLayout.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: #83695A;");

        // get the items and iterate over them
        List<Customers> customers = CustomersRepository.getCustomers();
        for (Customers customer : customers) {
            // create a new ItemCard
            Integer id = customer.getId();
            if (customer.getName() != "") {
                CustomerCard customerCard = new CustomerCard(id);

                // add the action to the view detail button
                customerCard.getViewDetailBtn().setOnAction(e -> {
                    try {
                        // load the item detail page
    //                    customerDetailPage.readData(finalId.intValue());
                        mulscreens.getChildren().get(0).setVisible(false);

                        mulscreens.getChildren().get(1).setVisible(true);
                        mulscreens.getChildren().get(2).setVisible(false);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });

                // check if the search field is empty or if the item name matches the search field
    //            if (searchBar.getSearchField().getText().isEmpty() ||
    //                    ((String) item.get("name")).toLowerCase().contains(searchBar.getSearchField().getText().toLowerCase())) {
                    // add the card to the new card layout
                GridPane.setMargin(customerCard.getCardContainer(), new Insets(30));
                newCardLayout.getChildren().add(customerCard.getCardContainer());

            }
        }
//
   // set the new card layout as the content of the scroll container
        scrollPane.setContent(newCardLayout);

        // reset the column and row counts
    }

}

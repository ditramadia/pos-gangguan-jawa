package com.example.if2210_tb2_nge.pages;

import com.example.if2210_tb2_nge.components.ConfirmationBox;
import com.example.if2210_tb2_nge.components.CustomerCard;
import com.example.if2210_tb2_nge.components.SearchBar;
import com.example.if2210_tb2_nge.entity.Members;
import com.example.if2210_tb2_nge.repository.CustomersRepository;
import javafx.animation.PauseTransition;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;
import lombok.Getter;

import java.util.List;

public class CustomerPage {
    @Getter
    private Tab tab;
    private StackPane mulscreens;
    private BorderPane pageContainer;
    private CustomerDetailPage customerDetailPage;
    private SearchBar searchBar;
    private ScrollPane scrollPane;
    private VBox cardContent;
    private VBox contentContainer;
    private Label header;
    private ConfirmationBox confirmationBox;


    public CustomerPage() throws Exception {
        tab = new Tab("Customer");
        tab.setStyle("-fx-background-radius: 10 10 0 0;");
        TabPane tabPane = new TabPane();
        tabPane.getTabs().add(tab);

        mulscreens = new StackPane();
        pageContainer = new BorderPane();
        scrollPane = new ScrollPane();
        cardContent = new VBox();

        contentContainer = new VBox();
        pageContainer.setCenter(contentContainer);

        customerDetailPage = new CustomerDetailPage();
        customerDetailPage.getBackBtn().setOnAction(e -> {
            customerDetailPage.resetPage();
            mulscreens.getChildren().get(0).setVisible(true);
            mulscreens.getChildren().get(1).setVisible(false);
            try {
                updateCard();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        //hide
        mulscreens.getChildren().add(pageContainer);
        mulscreens.getChildren().add(customerDetailPage.getPageContainer());
        mulscreens.getChildren().get(1).setVisible(false);

        header = new Label("MEMBER");
        Font fontTitle = Font.loadFont("file:src/assets/Montserrat-Bold.ttf", 50);
        header.setFont(fontTitle);
        header.setStyle("-fx-text-fill: #478660;");
        header.setAlignment(Pos.CENTER);
        header.setPrefHeight(100);
        header.setPrefWidth(500);
        pageContainer.setAlignment(header, Pos.CENTER);
        pageContainer.setTop(header);

        //search bar
        searchBar = new SearchBar();
        contentContainer.getChildren().add(searchBar.getSearchBarContainer());
        searchBar.getSearchField().textProperty().addListener((observable, oldValue, newValue) -> {
            // Check if the text field has lost focus
            PauseTransition pause = new PauseTransition(Duration.millis(10));
            pause.setOnFinished(event -> {
                try {
                    this.updateCard();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            pause.play();
        });

        // add card
        this.updateCard();
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setFitToWidth(true);
        contentContainer.setMargin(scrollPane, new Insets(25, 0, 0, 0));
        BorderPane.setAlignment(scrollPane, Pos.CENTER);
        contentContainer.getChildren().add(scrollPane);

        tab.setContent(mulscreens);
    }

    public void updateCard() throws Exception {
        VBox newCardLayout = new VBox();
        newCardLayout.setSpacing(15);
        newCardLayout.setAlignment(Pos.CENTER);

        // get the items and iterate over them
        List<Members> customers = CustomersRepository.getMembersOnly();
        for (Members customer : customers) {
            // create a new ItemCard
            Integer id = customer.getId();
            if (!customer.getName().equals("")) {
                CustomerCard customerCard = new CustomerCard(id);

                // add the action to the view detail button
                customerCard.getViewDetailBtn().setOnAction(e -> {
                    try {
                        // load the item detail page
                        customerDetailPage.readData(customer.getId());
                        mulscreens.getChildren().get(0).setVisible(false);
                        mulscreens.getChildren().get(1).setVisible(true);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });

                if (searchBar.getSearchField().getText().isEmpty() ||
                        customer.getName().toLowerCase().contains(searchBar.getSearchField().getText().toLowerCase())) {
                    newCardLayout.getChildren().add(customerCard.getCustomerContainer());
                }
            }
        }
        scrollPane.setContent(newCardLayout);
    }
}

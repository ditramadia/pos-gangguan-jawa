package com.example.if2210_tb2_nge.pages;

import com.example.if2210_tb2_nge.components.ItemCard;
import com.example.if2210_tb2_nge.components.MenuCard;
import com.example.if2210_tb2_nge.components.SearchBar;
import com.example.if2210_tb2_nge.controller.ItemController;
import com.example.if2210_tb2_nge.controller.TransactionController;
import com.example.if2210_tb2_nge.entity.Bill;
import com.example.if2210_tb2_nge.entity.CartItem;
import com.example.if2210_tb2_nge.entity.Items;
import com.example.if2210_tb2_nge.repository.ItemsRepository;
import javafx.animation.PauseTransition;
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

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuPage {
    @Getter
    private Tab tab;
    private StackPane tabContainer;
    private BorderPane pageContainer;
    private VBox contentContainer;
    private ScrollPane scrollPane;
    private GridPane cardContainer;
    private Label header;
    private SearchBar searchBar;
    private Button cart;
    private int row = 1;
    private int column = 0;
    private List<MenuCard> menuCards;
    List<CartItem> cartItems;
    private CartPage cartPage;

    public MenuPage() throws Exception {
        // Cart Items
        cartItems = new ArrayList<>();

        // Tab
        tab = new Tab("Menu");
        tab.setStyle("-fx-background-radius: 10 10 0 0;");
        TabPane tabPane = new TabPane();
        tabPane.getTabs().add(tab);

        // Tab Container
        tabContainer = new StackPane();
        tab.setContent(tabContainer);

        // Page Container
        pageContainer = new BorderPane();
        tabContainer.getChildren().add(pageContainer);

        // Cart page
        cartPage = new CartPage();
        cartPage.getBackBtn().setOnAction(e -> {
            tabContainer.getChildren().get(0).setVisible(true);
            tabContainer.getChildren().get(1).setVisible(false);
        });
        tabContainer.getChildren().add(cartPage.getPageContainer());
        tabContainer.getChildren().get(1).setVisible(false);

        // Content Container
        contentContainer = new VBox();
        pageContainer.setCenter(contentContainer);

        //

        // Title
        header = new Label("MENU");
        Font fontTitle = Font.loadFont("file:src/assets/Montserrat-Bold.ttf", 50);
        header.setFont(fontTitle);
        header.setStyle("-fx-text-fill: #478660;");
        header.setAlignment(Pos.CENTER);
        header.setPrefHeight(100);
        header.setPrefWidth(500);
        pageContainer.setAlignment(header, Pos.CENTER);
        pageContainer.setTop(header);

        // search bar
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

        // scroll container
        scrollPane = new ScrollPane();
        contentContainer.getChildren().add(scrollPane);

        // cards container
        cardContainer = new GridPane();
        scrollPane.setContent(cardContainer);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        this.updateCard();

        // Cart button
        cart = new Button("Cart");
        cart.setOnAction(e -> {
            tabContainer.getChildren().get(0).setVisible(false);
            tabContainer.getChildren().get(1).setVisible(true);
            cartItems = new ArrayList<>();
            for (MenuCard menuCard : menuCards) {
                if (!menuCard.getQuantity().getText().isEmpty()) {
                    ItemController.setItemInstance(menuCard.getCurrentID());
                    CartItem item = new CartItem(ItemController.getItemInstance(), Integer.parseInt(menuCard.getQuantity().getText()));
                    cartItems.add(item);
                }
            }
            TransactionController.setBillInstance(new Bill(cartItems));
            cartPage.setCart();
        });
        cart.setStyle("-fx-background-color: #478660; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");
        cart.setPrefWidth(200);
        cart.setPrefHeight(50);
        BorderPane.setMargin(cart, new Insets(10, 85, 10, 0));
        BorderPane.setAlignment(cart, Pos.BOTTOM_RIGHT);
        pageContainer.setBottom(cart);
    }

    public void updateCard() throws Exception {
        // create a new GridPane
        GridPane newCardLayout = new GridPane();
        menuCards = new ArrayList<>();

        // get the items and iterate over them
        List<Items> items = ItemsRepository.getItems();
        for (Items item : items) {
            // create a new ItemCard
            Integer id = item.getId();
            MenuCard itemCard = new MenuCard(id);
            menuCards.add(itemCard);

//            // check if the search field is empty or if the item name matches the search field
            if (searchBar.getSearchField().getText().isEmpty() ||
                    item.getName().toLowerCase().contains(searchBar.getSearchField().getText().toLowerCase())){
                // add the card to the new card layout
                GridPane.setMargin(itemCard.getCardContainer(), new Insets(30));
                newCardLayout.add(itemCard.getCardContainer(), column++, row);
                if (column == 6) {
                    row++;
                    column = 0;
                }
            }
        }

        // set the new card layout as the content of the scroll container
        scrollPane.setContent(newCardLayout);

        // reset the column and row counts
        column = 0;
        row = 1;
    }


}

package com.example.if2210_tb2_nge.pages;

import com.example.if2210_tb2_nge.components.MenuCard;
import com.example.if2210_tb2_nge.components.SearchBar;
import com.example.if2210_tb2_nge.controller.ItemController;
import com.example.if2210_tb2_nge.controller.TransactionController;
import com.example.if2210_tb2_nge.entity.Bill;
import com.example.if2210_tb2_nge.entity.CartItem;
import com.example.if2210_tb2_nge.entity.Items;
import com.example.if2210_tb2_nge.repository.ItemsRepository;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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
    private Label title;
    private SearchBar searchBar;
    private Button cart;
    private int row = 1;
    private int column = 0;
    private List<MenuCard> menuCards;
    List<CartItem> cartItems;
    private CartPage cartPage;

    public MenuPage() throws MalformedURLException {
        // Cart Items
        cartItems = new ArrayList<>();

        // Tab
        tab = new Tab("Menu");

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

        // Search bar
        searchBar = new SearchBar();
        contentContainer.getChildren().add(searchBar);

        // Scroll Pane
        scrollPane = new ScrollPane();
        contentContainer.getChildren().add(scrollPane);

        // Card Container
        cardContainer = new GridPane();
        scrollPane.setContent(cardContainer);

        // Title
        title = new Label("MENU");
        title.setFont(new Font(30));
        pageContainer.setTop(title);

        // Menu cards
        menuCards = new ArrayList<>();
        List<Items> items = ItemsRepository.getItems();
        for (Items item : items) {
            Integer id = item.getId();
            MenuCard card = new MenuCard(id);
            menuCards.add(card);
            GridPane.setMargin(card.getCardContainer(), new Insets(30));
            cardContainer.add(card.getCardContainer(), column, row);
            column++;
            if (column == 6) {
                column = 0;
                row++;
            }
        }

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
        pageContainer.setBottom(cart);
    }

}

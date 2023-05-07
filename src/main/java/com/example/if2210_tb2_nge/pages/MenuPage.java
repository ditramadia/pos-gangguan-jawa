package com.example.if2210_tb2_nge.pages;

import com.example.if2210_tb2_nge.components.MenuCard;
import com.example.if2210_tb2_nge.components.SearchBar;
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
    private Button checkout;
    private int row = 1;
    private int column = 0;
    private List<MenuCard> menuCards;
    List<CartItem> cartItems;
    private CartPage cartPage;

    public MenuPage() {
        tab = new Tab("Menu");
        tabContainer = new StackPane();
        pageContainer = new BorderPane();
        contentContainer = new VBox();
        scrollPane = new ScrollPane();
        cardContainer = new GridPane();
        title = new Label("MENU");
        title.setFont(new Font(30));
        searchBar = new SearchBar();
        checkout = new Button("Checkout");
        cartItems = new ArrayList<>();
        menuCards = new ArrayList<>();
        cartPage = new CartPage(cartItems);


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

        contentContainer.getChildren().addAll(searchBar.getSearchBarContainer(), scrollPane);
        scrollPane.setContent(cardContainer);
        pageContainer.setCenter(contentContainer);

        pageContainer.setTop(title);
        pageContainer.setBottom(checkout);

        tabContainer.getChildren().add(pageContainer);
        tabContainer.getChildren().add(cartPage.getPageContainer());
        tabContainer.getChildren().get(1).setVisible(false);

        cartPage.getBackBtn().setOnAction(e -> {
            tabContainer.getChildren().get(0).setVisible(true);
            tabContainer.getChildren().get(1).setVisible(false);
        });

        checkout.setOnAction(e -> {
            tabContainer.getChildren().get(0).setVisible(false);
            tabContainer.getChildren().get(1).setVisible(true);
            cartItems = new ArrayList<>();
            for (MenuCard menuCard : menuCards) {
                if (!menuCard.getQuantity().getText().isEmpty()) {
                    CartItem item = new CartItem(menuCard.getCurrentID(), Integer.parseInt(menuCard.getQuantity().getText()));
                    cartItems.add(item);
                }


            }
            cartPage.setCart(cartItems);
            System.out.println(cartItems);
            System.out.println(menuCards.size());
        });

        tab.setContent(tabContainer);
    }

}

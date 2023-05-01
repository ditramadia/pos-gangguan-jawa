package com.example.if2210_tb2_nge.pages;

import com.example.if2210_tb2_nge.components.ItemCard;
import com.example.if2210_tb2_nge.components.SearchBar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Tab;
import javafx.scene.control.Label;
import lombok.Getter;

public class MenuPage {
    @Getter
    private Tab tab;
    private BorderPane pageContainer;
    private VBox contentContainer;
    private Label header;
    private SearchBar searchBar;
    private Button newItemBtn;
    private ScrollPane scrollContainer;
    private GridPane cardContainer;

    public MenuPage(){
        // tab
        tab = new Tab("Inventory");

        // page container
        pageContainer = new BorderPane();

        // content container
        contentContainer = new VBox();
        pageContainer.setCenter(contentContainer);

        // header
        header = new Label("INVENTORY");
        BorderPane.setAlignment(header, Pos.TOP_CENTER);
        pageContainer.setTop(header);

        // search bar
        searchBar = new SearchBar();
//        contentContainer.getChildren().add(searchBar.getSearchBarContainer());

        // scroll container
        scrollContainer = new ScrollPane();
        contentContainer.getChildren().add(scrollContainer);

        // teruntuk mas kelvin, silakan buat container cards
        ItemCard itemCard = new ItemCard();
        ItemCard itemCard1 = new ItemCard();
        ItemCard itemCard2 = new ItemCard();
        ItemCard itemCard3 = new ItemCard();
        ItemCard itemCard4 = new ItemCard();
        ItemCard itemCard5 = new ItemCard();
        ItemCard itemCard6 = new ItemCard();
        ItemCard itemCard7 = new ItemCard();
        VBox card = itemCard.getCardContainer();

        // cards container
        cardContainer = new GridPane();
        cardContainer.add(itemCard.getCardContainer(),0,0);
        cardContainer.add(itemCard1.getCardContainer(),0,1);
        cardContainer.add(itemCard2.getCardContainer(),0,2);
        cardContainer.add(itemCard3.getCardContainer(),0,3);
        cardContainer.add(itemCard4.getCardContainer(),0,4);
        cardContainer.add(itemCard5.getCardContainer(),0,5);
        cardContainer.add(itemCard6.getCardContainer(),0,6);
        cardContainer.add(itemCard7.getCardContainer(),0,7);
        scrollContainer.setContent(cardContainer);
//        GridPane.setMargin(card, new Insets(10));


        // new item button
        newItemBtn = new Button("New Item");
        BorderPane.setAlignment(newItemBtn, Pos.BOTTOM_RIGHT);
        pageContainer.setBottom(newItemBtn);

        tab.setContent(pageContainer);
    }
}

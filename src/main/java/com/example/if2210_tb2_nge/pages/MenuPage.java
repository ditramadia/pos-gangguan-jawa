package com.example.if2210_tb2_nge.pages;

import com.example.if2210_tb2_nge.components.ItemCard;
import com.example.if2210_tb2_nge.components.SearchBar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
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
        contentContainer.getChildren().add(searchBar.getSearchBarContainer());

        // cards container
        // teruntuk mas kelvin, silakan buat container cards
        ItemCard itemCard = new ItemCard();
        contentContainer.getChildren().add(itemCard.getCardContainer());

        // new item button
        newItemBtn = new Button("New Item");
        BorderPane.setAlignment(newItemBtn, Pos.BOTTOM_RIGHT);
        pageContainer.setBottom(newItemBtn);

        tab.setContent(pageContainer);
    }
}

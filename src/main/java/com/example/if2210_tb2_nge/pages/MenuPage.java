package com.example.if2210_tb2_nge.pages;

import com.example.if2210_tb2_nge.components.MenuCard;
import com.example.if2210_tb2_nge.components.SearchBar;
import com.example.if2210_tb2_nge.entity.Items;
import com.example.if2210_tb2_nge.repository.ItemsRepository;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import lombok.Getter;

import java.util.List;

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
    private int row = 1;
    private int column = 0;

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


        List<Items> items = ItemsRepository.getItems();
        for (Items item : items) {
            Integer id = item.getId();
            MenuCard card = new MenuCard(id);
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

        tabContainer.getChildren().add(pageContainer);

        tab.setContent(tabContainer);

    }

}

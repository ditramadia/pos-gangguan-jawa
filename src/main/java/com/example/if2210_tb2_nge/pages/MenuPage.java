package com.example.if2210_tb2_nge.pages;

import com.example.if2210_tb2_nge.components.MenuCard;
import com.example.if2210_tb2_nge.components.SearchBar;
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


        for (int i = 0; i < 10; i++){
            MenuCard newMenuCard = new MenuCard();
            GridPane.setMargin(newMenuCard.getCardContainer(), new Insets(30));
            cardContainer.add(newMenuCard.getCardContainer(), column++, row);
            if (column == 6){
                row++;
                column = 0;
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

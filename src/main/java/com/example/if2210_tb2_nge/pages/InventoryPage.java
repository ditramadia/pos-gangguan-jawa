package com.example.if2210_tb2_nge.pages;

import com.example.if2210_tb2_nge.components.ItemCard;
import com.example.if2210_tb2_nge.components.SearchBar;
import com.example.if2210_tb2_nge.repository.ItemsRepository;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import java.util.Map;

public class InventoryPage implements EventHandler<ActionEvent> {
    @Getter
    private Tab tab;
    private StackPane mulscreens;
    private ItemDetailPage itemDetailPage;
    private GridPane cardLayout;
    private BorderPane pageContainer;
    private VBox contentContainer;
    private Label header;
    private SearchBar searchBar;
    private Button newItemBtn;
    private ScrollPane scrollContainer;

    private int column = 0;
    private int row = 1;

    public InventoryPage() throws Exception {
        // tab
        tab = new Tab("Inventory");
        tab.setStyle("-fx-background-radius: 10 10 0 0;");
        TabPane tabPane = new TabPane();
        tabPane.getTabs().add(tab);

        // pages container
        mulscreens = new StackPane();
        itemDetailPage = new ItemDetailPage();


        // page container
        pageContainer = new BorderPane();

        // content container
        contentContainer = new VBox();
        pageContainer.setCenter(contentContainer);
        mulscreens.getChildren().add(pageContainer);
        mulscreens.getChildren().add(itemDetailPage.getPageContainer());
        mulscreens.getChildren().get(1).setVisible(false);

        // header
        header = new Label("INVENTORY");
        header.setFont(new Font(30));
        header.setPrefHeight(100);
        header.setPrefWidth(300);
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
        scrollContainer = new ScrollPane();
        contentContainer.getChildren().add(scrollContainer);
        // cards container
        cardLayout = new GridPane();
        cardLayout.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: #83695A;");
        scrollContainer.setContent(cardLayout);

        // teruntuk mas kelvin, silakan buat container cards
        this.updateCard();


        // cards container

//        GridPane.setMargin(card, new Insets(10));


        // new item button
        newItemBtn = new Button("New Item");
        BorderPane.setAlignment(newItemBtn, Pos.BOTTOM_RIGHT);
        pageContainer.setBottom(newItemBtn);
        tab.setContent(mulscreens);

        itemDetailPage.getBackBtn().setOnAction(e -> {
            itemDetailPage.resetPage();
            mulscreens.getChildren().get(0).setVisible(true);
            mulscreens.getChildren().get(1).setVisible(false);
        });

    }

    @Override
    public void handle(ActionEvent actionEvent) {


        mulscreens.getChildren().get(0).setVisible(false);
        mulscreens.getChildren().get(1).setVisible(true);
    }


    public void updateCard() throws Exception {
        // create a new GridPane
        GridPane newCardLayout = new GridPane();
        newCardLayout.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: #83695A;");

        // get the items and iterate over them
        List<Map<String, Object>> items = ItemsRepository.getItems();
        for (Map<String, Object> item : items) {
            // create a new ItemCard
            Double id = (Double) item.get("id");
            ItemCard itemCard = new ItemCard(id.intValue());

            // add the action to the view detail button
            itemCard.getViewDetailBtn().setOnAction(e -> {
                try {
                    // load the item detail page
//                itemDetailPage.loadData(id.intValue());
                    mulscreens.getChildren().get(0).setVisible(false);
                    mulscreens.getChildren().get(1).setVisible(true);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });

            // check if the search field is empty or if the item name matches the search field
            if (searchBar.getSearchField().getText().isEmpty() ||
                    ((String) item.get("name")).toLowerCase().contains(searchBar.getSearchField().getText().toLowerCase())) {
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
        scrollContainer.setContent(newCardLayout);

        // reset the column and row counts
        column = 0;
        row = 1;
    }
}

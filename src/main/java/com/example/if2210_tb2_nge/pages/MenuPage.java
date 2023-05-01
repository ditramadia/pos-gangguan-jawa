package com.example.if2210_tb2_nge.pages;

import com.example.if2210_tb2_nge.components.ItemCard;
import com.example.if2210_tb2_nge.components.SearchBar;
import com.example.if2210_tb2_nge.controller.ItemController;
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
import lombok.Getter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MenuPage implements EventHandler<ActionEvent> {
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

    public MenuPage() throws Exception {
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
//        contentContainer.getChildren().add(searchBar.getSearchBarContainer());

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

    }

    @Override
    public void handle(ActionEvent actionEvent) {


        System.out.println("View Detail Button Clicked");
        mulscreens.getChildren().get(0).setVisible(false);
        mulscreens.getChildren().get(1).setVisible(true);
    }

    public void updateCard() throws Exception {
        JSONObject jsonObj = ItemController.readItemsJSON("src/main/java/com/example/if2210_tb2_nge/database/Items.json");
        JSONArray itemsArray = (JSONArray) jsonObj.get("items");
        for (Object itemsObj : itemsArray) {
            JSONObject product = (JSONObject) itemsObj;
            Long id = (Long) product.get("id");
            ItemCard itemCard8 = new ItemCard(id.intValue());

            itemCard8.getViewDetailBtn().setOnAction(e -> {
                try {
                    itemDetailPage.loadData(id.intValue());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                mulscreens.getChildren().get(0).setVisible(false);
                mulscreens.getChildren().get(1).setVisible(true);
            });

            GridPane.setMargin(itemCard8.getCardContainer(), new Insets(30));
            cardLayout.add(itemCard8.getCardContainer(), column++, row);

            if (column == 6){
                row++;
                column = 0;
            }
        }
    }
}

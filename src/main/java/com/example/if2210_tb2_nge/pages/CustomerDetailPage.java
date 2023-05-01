package com.example.if2210_tb2_nge.pages;

import com.example.if2210_tb2_nge.controller.ItemController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CustomerDetailPage {
    @Getter
    private BorderPane pageContainer;
    private VBox contentContainer;
    private Label title;
    private Label name;
    private Label price;
    private Label telephone;
    private Label buyPrice;
    private Label category;

    public CustomerDetailPage() {
        pageContainer = new BorderPane();
        contentContainer = new VBox();
        pageContainer.setCenter(contentContainer);

        title = new Label("ITEM DETAILS");
        name = new Label("Name: ");
        price = new Label("Price: ");
        telephone = new Label("Stock: ");
        buyPrice = new Label("Buy Price: ");
        category = new Label("Category: ");

        contentContainer.getChildren().add(title);
        contentContainer.getChildren().add(name);
        contentContainer.getChildren().add(price);
        contentContainer.getChildren().add(telephone);
        contentContainer.getChildren().add(buyPrice);
        contentContainer.getChildren().add(category);
    }

    public void loadData(int id) throws Exception {
        // load data from database
        // set data to labels

        JSONObject jsonObj = ItemController.readItemsJSON("src/main/java/com/example/if2210_tb2_nge/database/Items.json");
        JSONArray itemsArray = (JSONArray) jsonObj.get("items");
        for (Object itemsObj : itemsArray) {
            JSONObject product = (JSONObject) itemsObj;
            if (Integer.parseInt(product.get("id").toString()) == id) {
                name.setText("Name: " + product.get("name").toString());
                price.setText("Price: " + product.get("price").toString());
                telephone.setText("Stock: " + product.get("stock").toString());
                buyPrice.setText("Buy Price: " + product.get("buyPrice").toString());
                category.setText("Category: " + product.get("category").toString());
            }
        }

    }
}

package com.example.if2210_tb2_nge.pages;

import com.example.if2210_tb2_nge.controller.ItemController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.MalformedURLException;

public class ItemDetailPage {
    @Getter
    private BorderPane pageContainer;
    private VBox contentContainer;
    private Label title;
    private Label name;
    private Label price;
    private Label stock;
    private Label buyPrice;
    private Label category;
    private Label line;
    private Label stockEdit;
    private Label priceEdit;
    private TextField stockField;
    private TextField priceField;

    private Integer id;


    public ItemDetailPage() {
        pageContainer = new BorderPane();
        contentContainer = new VBox();
        pageContainer.setCenter(contentContainer);

        title = new Label("ITEM DETAILS");
        name = new Label("Name: ");
        price = new Label("Price: ");
        stock = new Label("Stock: ");
        buyPrice = new Label("Buy Price: ");
        category = new Label("Category: ");
        line = new Label("--------------------------------------------------");
        stockEdit = new Label("Edit Stock: ");
        priceEdit = new Label("Edit Price: ");

        stockField = new TextField();
        priceField = new TextField();

        contentContainer.getChildren().add(title);
        contentContainer.getChildren().add(name);
        contentContainer.getChildren().add(price);
        contentContainer.getChildren().add(stock);
        contentContainer.getChildren().add(buyPrice);
        contentContainer.getChildren().add(category);

        contentContainer.getChildren().add(line);

        contentContainer.getChildren().add(stockEdit);
        contentContainer.getChildren().add(stockField);

        contentContainer.getChildren().add(priceEdit);
        contentContainer.getChildren().add(priceField);

        Button updateStockButton = new Button("Update");
        updateStockButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    updateData(id);
                    // refresh page
                    loadData(id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        contentContainer.getChildren().add(updateStockButton);
    }

    public void loadData(int id) throws Exception {
        this.id = id;

        JSONObject jsonObj = ItemController.readItemsJSON("src/main/java/com/example/if2210_tb2_nge/database/Items.json");
        JSONArray itemsArray = (JSONArray) jsonObj.get("items");
        for (Object itemsObj : itemsArray) {
            JSONObject product = (JSONObject) itemsObj;
            if (Integer.parseInt(product.get("id").toString()) == id) {
                name.setText("Name: " + product.get("name").toString());
                price.setText("Price: " + product.get("price").toString());
                stock.setText("Stock: " + product.get("stock").toString());
                buyPrice.setText("Buy Price: " + product.get("buyPrice").toString());
                category.setText("Category: " + product.get("category").toString());
            }
        }

    }

    public void updateData(int id) throws Exception {
        JSONObject jsonObj = ItemController.readItemsJSON("src/main/java/com/example/if2210_tb2_nge/database/Items.json");
        JSONArray itemsArray = (JSONArray) jsonObj.get("items");
        for (Object itemsObj : itemsArray) {
            JSONObject item = (JSONObject) itemsObj;
            if (Integer.parseInt(item.get("id").toString()) == id) {
                ItemController.updateItemsJSON(id, item.get("name").toString(), Integer.parseInt(priceField.getText()), Integer.parseInt(item.get("buyPrice").toString()), Integer.parseInt(stockField.getText()), item.get("category").toString(), item.get("image").toString()) ;
                break;
            }
        }
    }
}

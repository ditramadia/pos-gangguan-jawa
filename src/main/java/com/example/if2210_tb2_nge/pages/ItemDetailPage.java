package com.example.if2210_tb2_nge.pages;

import com.example.if2210_tb2_nge.controller.ItemController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import lombok.Getter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.MalformedURLException;

public class ItemDetailPage {
    @Getter
    private BorderPane pageContainer;
    private VBox contentContainer;
    private HBox textContainer;
    private HBox stockPrice;
    private Label title;
    private Label name;
    private Label price;
    private Label stock;
    private Label buyPrice;
    private Label category;
    private Label line;
    private Label stockEdit;
    private Label priceEdit;
    private TextField nameField;
    private TextField stockField;
    private TextField priceField;
    private Button updateBtn;
    @Getter
    private Button backBtn;

    private Integer id;
    private boolean editMode = false;


    public ItemDetailPage() {
        pageContainer = new BorderPane();
        contentContainer = new VBox();
        pageContainer.setCenter(contentContainer);
        textContainer = new HBox();
        stockPrice = new HBox();
        textContainer.setAlignment(Pos.CENTER);
        textContainer.setPrefHeight(50);
        stockPrice.setAlignment(Pos.TOP_CENTER);
        stockPrice.setPrefHeight(50);

        title = new Label("ITEM DETAILS");
        title.setFont(new Font(60));
        title.setPrefHeight(70);
        title.setPrefWidth(500);
        name = new Label("Name: ");
        price = new Label("Price: ");
        stock = new Label("Stock: ");
        buyPrice = new Label("Buy Price: ");
        category = new Label("Category: ");
        line = new Label("--------------------------------------------------");
        stockEdit = new Label("Edit Stock: ");
        priceEdit = new Label("Edit Price: ");

        contentContainer.setAlignment(Pos.TOP_CENTER);

        nameField = new TextField();
        nameField.setPrefWidth(500);
        nameField.setPrefHeight(30);
        stockField = new TextField();
        nameField.setPrefWidth(250);
        nameField.setPrefHeight(50);
        priceField = new TextField();
        nameField.setPrefWidth(250);
        nameField.setPrefHeight(50);

        nameField.setEditable(false);
        stockField.setEditable(false);
        priceField.setEditable(false);

        contentContainer.getChildren().add(title);
        contentContainer.getChildren().add(name);
        contentContainer.getChildren().add(price);
        contentContainer.getChildren().add(stock);
        contentContainer.getChildren().add(buyPrice);
        contentContainer.getChildren().add(category);

        contentContainer.getChildren().add(line);

        textContainer.getChildren().addAll(name,nameField);
        contentContainer.getChildren().add(textContainer);

        stockPrice.getChildren().addAll(stock,stockField,price,priceField);

        contentContainer.getChildren().add(stockPrice);

        updateBtn = new Button("Edit");
        updateBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(editMode == true) {
                    try {
//                        updateData(id);
                        // refresh page
//                        loadData(id);
                        nameField.setEditable(false);
                        stockField.setEditable(false);
                        priceField.setEditable(false);
                        updateBtn.setText("Edit");
                        editMode = false;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else{
                    updateBtn.setText("Update");
                    nameField.setEditable(true);
                    stockField.setEditable(true);
                    priceField.setEditable(true);
                    editMode = true;
                }
            }
        });

        backBtn = new Button("Back");
        contentContainer.getChildren().add(updateBtn);
        contentContainer.getChildren().add(backBtn);
    }

//    public void loadData(int id) throws Exception {
//        this.id = id;
//
//        JSONObject jsonObj = ItemController.readItemsJSON("src/main/java/com/example/if2210_tb2_nge/database/Items.json");
//        JSONArray itemsArray = (JSONArray) jsonObj.get("items");
//        for (Object itemsObj : itemsArray) {
//            JSONObject product = (JSONObject) itemsObj;
//            if (Integer.parseInt(product.get("id").toString()) == id) {
//                nameField.setText(product.get("name").toString());
//                priceField.setText(product.get("price").toString());
//                stockField.setText(product.get("stock").toString());
//                buyPrice.setText("Buy Price: " + product.get("buyPrice").toString());
//                category.setText("Category: " + product.get("category").toString());
//            }
//        }
//
//    }

    public void resetPage(){
        nameField.setEditable(false);
        stockField.setEditable(false);
        priceField.setEditable(false);
        updateBtn.setText("Edit");
        editMode = false;
    }

    public void updateData(int id) throws Exception {
//        JSONObject jsonObj = ItemController.readItemsJSON("src/main/java/com/example/if2210_tb2_nge/database/Items.json");
//        JSONArray itemsArray = (JSONArray) jsonObj.get("items");
//        for (Object itemsObj : itemsArray) {
//            JSONObject item = (JSONObject) itemsObj;
//            if (Integer.parseInt(item.get("id").toString()) == id) {
//                ItemController.updateItemsJSON(id, item.get("name").toString(), Integer.parseInt(priceField.getText()), Integer.parseInt(item.get("buyPrice").toString()), Integer.parseInt(stockField.getText()), item.get("category").toString(), item.get("image").toString()) ;
//                break;
//            }
//        }
    }
}

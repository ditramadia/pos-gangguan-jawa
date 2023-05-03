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
    private Label noTelp;
    private Label vip;
    private Label active;

    public CustomerDetailPage() {
        pageContainer = new BorderPane();
        contentContainer = new VBox();
        pageContainer.setCenter(contentContainer);

        title = new Label("CUSTOMER DETAILS");
        name = new Label("Name: ");
        noTelp = new Label("Telephone: ");
        vip = new Label("VIP: ");
        active = new Label("Active: ");

        contentContainer.getChildren().add(title);
        contentContainer.getChildren().add(name);
        contentContainer.getChildren().add(noTelp);
        contentContainer.getChildren().add(vip);
        contentContainer.getChildren().add(active);
    }

    public void loadData(int id) throws Exception {
        // load data from database
        // set data to labels

//        JSONObject jsonObj = ItemController.readItemsJSON("src/main/java/com/example/if2210_tb2_nge/database/Customers.json");
//        JSONArray itemsArray = (JSONArray) jsonObj.get("customers");
//        for (Object itemsObj : itemsArray) {
//            JSONObject product = (JSONObject) itemsObj;
//            if (Integer.parseInt(product.get("id").toString()) == id) {
//                name.setText("Name: " + product.get("name").toString());
//                noTelp.setText("Telephone: " + product.get("noTelp").toString());
//                vip.setText("VIP: " + product.get("vip").toString());
//                active.setText("Active: " + product.get("active").toString());
//            }
//        }
    }
}

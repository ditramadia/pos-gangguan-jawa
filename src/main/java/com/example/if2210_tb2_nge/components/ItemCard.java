package com.example.if2210_tb2_nge.components;

import com.example.if2210_tb2_nge.controller.ItemController;
import com.example.if2210_tb2_nge.repository.ItemsRepository;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import lombok.Getter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.List;
import java.util.Map;

public class ItemCard extends Control{
    @Getter
    private VBox cardContainer;
    private String imageUrl;
    private Image image;
    private ImageView imageView;
    @Getter
    private Label itemName;
    @Getter
    private Button viewDetailBtn;

    public ItemCard(int i) throws Exception {
        // card container
        cardContainer = new VBox();cardContainer.setStyle("-fx-background-color: #D7CDC7; -fx-background-radius: 20");
        cardContainer.setPrefWidth(250);
        cardContainer.setPrefHeight(250);
        cardContainer.setStyle("-fx-background-color: #D7CDC7; -fx-background-radius: 20");
        cardContainer.setPrefWidth(250);
        cardContainer.setPrefHeight(250);

        // Data
        List<Map<String, Object>> items = ItemsRepository.getItems();
        imageUrl = "data:image/jpeg;base64,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";
        for (Map<String, Object> item : items) {
            Double id = (Double) item.get("id");
            String image = (String) item.get("image");
            if (id.intValue() == i){
                // item name
                if (image.length() > 5){
                    imageUrl = (String) item.get("image");
                }
                itemName = new Label((String) item.get("name"));
                cardContainer.getChildren().add(itemName);
                itemName.setFont(new Font("Arial", 20));
                cardContainer.setAlignment(Pos.TOP_CENTER);
                VBox.setMargin(itemName, new Insets(20,0,0,0));
            }
        }

        // image
        image = new Image(imageUrl);
        imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setStyle("-fx-background-radius: 8;");
        cardContainer.getChildren().add(imageView);
        VBox.setMargin(imageView, new Insets(20,0,0,0));


        // view detail button
        viewDetailBtn = new Button("View details");
        VBox.setMargin(viewDetailBtn, new Insets(30, 0,0,0));
//        viewDetailBtn.setSkin(new MyButtonSkin(viewDetailBtn));
        File cssFile = new File("src/main/java/com/example/if2210_tb2_nge/style/buttonstyle.css");
        String cssUrl = cssFile.toURI().toURL().toExternalForm();
        cardContainer.getChildren().add(viewDetailBtn);
        cardContainer.getStylesheets().add(cssUrl);
//        cardContainer.getStylesheets().add(getClass().getResource("src/main/java/com/example/if2210_tb2_nge/style/style.css").toExternalForm());
    }
}

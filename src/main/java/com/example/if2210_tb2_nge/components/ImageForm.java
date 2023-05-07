package com.example.if2210_tb2_nge.components;

import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import lombok.Getter;
import lombok.Setter;

public class ImageForm extends Control {
    @Getter
    private StackPane imageContainer;
    private ImageView image;
    @Getter
    private Button editBtn;
    @Getter
    @Setter
    private String imgUrl;

    public ImageForm(String imgPath) {
        this.imgUrl = "https://assets.pikiran-rakyat.com/crop/0x0:0x0/x/photo/2021/01/28/2114811954.jpg";
        if (imgPath != null) {
            this.imgUrl = imgPath;
        }
        // image container
        imageContainer = new StackPane();

        // image view
        image = new ImageView(new Image(imgUrl));
        image.setFitHeight(200);
        image.setFitWidth(200);
        imageContainer.getChildren().add(image);

        // edit button
        editBtn = new Button("Edit");
        editBtn.setStyle("-fx-background-color: #83695A; -fx-text-fill: white;");
        imageContainer.getChildren().add(editBtn);
    }

    public void setIsDisable(Boolean isDisable) {
        if (isDisable) {
            editBtn.setVisible(false);
        } else {
            editBtn.setVisible(true);
        }
    }
}

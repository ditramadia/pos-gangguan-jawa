package com.example.if2210_tb2_nge.components;

import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import lombok.Getter;

public class ImageForm extends Control {
    @Getter
    private StackPane imageContainer;
    private ImageView image;
    @Getter
    private Button editBtn;
    @Getter
    private String imgUrl;

    public ImageForm(String imgPath) {
        this.imgUrl = imgPath;
        // image container
        imageContainer = new StackPane();

        // image view
        image = new ImageView(new Image(imgPath));
        image.setFitHeight(300);
        image.setFitWidth(300);
        imageContainer.getChildren().add(image);

        // edit button
        editBtn = new Button("Edit");
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

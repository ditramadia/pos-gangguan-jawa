package com.example.if2210_tb2_nge.pages;

import com.example.if2210_tb2_nge.components.ImageForm;
import com.example.if2210_tb2_nge.components.TextFieldForm;
import com.example.if2210_tb2_nge.controller.ItemController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import lombok.Getter;

public class NewItemPage {
    @Getter
    private VBox pageContainer;
    private Label header;
    @Getter
    private ImageForm itemImage;
    @Getter
    private TextFieldForm nameForm;
    @Getter
    private TextFieldForm categoryForm;
    private HBox horizontalTextFormContainer1;
    private HBox horizontalTextFormContainer2;
    private HBox horizontalTextFormContainer3;
    @Getter
    private TextFieldForm priceForm;
    @Getter
    private TextFieldForm buyPriceForm;
    @Getter
    private TextFieldForm stockForm;
    @Getter
    private Button saveBtn;
    @Getter
    private Button backBtn;

    public NewItemPage() {
        // page container
        pageContainer = new VBox();
        pageContainer.setAlignment(Pos.CENTER);

        // header
        header = new Label("NEW ITEM");
        Font fontTitle = Font.loadFont("file:src/assets/Montserrat-Bold.ttf", 50);
        header.setFont(fontTitle);
        header.setStyle("-fx-text-fill: #478660;");
        header.setPrefHeight(100);
        header.setAlignment(Pos.CENTER);
        pageContainer.getChildren().add(header);

        // images
        itemImage = new ImageForm("https://assets.pikiran-rakyat.com/crop/0x0:0x0/x/photo/2021/01/28/2114811954.jpg");
        pageContainer.getChildren().add(itemImage.getImageContainer());

        // name form
        horizontalTextFormContainer1 = new HBox();
        nameForm = new TextFieldForm("Name", "", 940);
        pageContainer.getChildren().add(horizontalTextFormContainer1);
        horizontalTextFormContainer1.setAlignment(Pos.CENTER);
        horizontalTextFormContainer1.getChildren().add(nameForm.getFormContainer());

        // category form
        horizontalTextFormContainer2 = new HBox();
        categoryForm = new TextFieldForm("Category", "", 940);
        pageContainer.getChildren().add(horizontalTextFormContainer2);
        horizontalTextFormContainer2.setAlignment(Pos.CENTER);
        horizontalTextFormContainer2.getChildren().add(categoryForm.getFormContainer());

        // horizontal text form container
        horizontalTextFormContainer3 = new HBox();
        pageContainer.getChildren().add(horizontalTextFormContainer3);
        horizontalTextFormContainer3.setAlignment(Pos.CENTER);
        horizontalTextFormContainer3.setSpacing(20);

        // price form
        priceForm = new TextFieldForm("Price", "", 300);
        horizontalTextFormContainer3.getChildren().add(priceForm.getFormContainer());

        // buy price form
        buyPriceForm = new TextFieldForm("Buy Price", "", 300);
        horizontalTextFormContainer3.getChildren().add(buyPriceForm.getFormContainer());

        // stock form
        stockForm = new TextFieldForm("Stock", "", 300);
        horizontalTextFormContainer3.getChildren().add(stockForm.getFormContainer());

        // save button
        saveBtn = new Button("Save");
        saveBtn.setStyle("-fx-background-color: #478660; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");
        saveBtn.setPrefWidth(400);
        VBox.setMargin(saveBtn, new Insets(50, 0, 0, 0));
        pageContainer.getChildren().add(saveBtn);

        // back button
        backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: #8C7466; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");
        backBtn.setPrefWidth(400);
        VBox.setMargin(backBtn, new Insets(20, 0, 0, 0));
        pageContainer.getChildren().add(backBtn);
    }

    public void resetPage() {
        nameForm.setValue("");
        categoryForm.setValue("");
        priceForm.setValue("");
        buyPriceForm.setValue("");
        stockForm.setValue("");
    }

    public Boolean isComplete(){
        System.out.println(nameForm.getValue());
        if (!nameForm.getValue().isEmpty()&& !categoryForm.getValue().isEmpty() && !priceForm.getValue().isEmpty() && !buyPriceForm.getValue().isEmpty() && !stockForm.getValue().isEmpty()){
            return true;
        }
        else {
            return false;
        }
    }
}

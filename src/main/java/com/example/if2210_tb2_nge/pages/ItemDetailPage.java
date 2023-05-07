package com.example.if2210_tb2_nge.pages;

import com.example.if2210_tb2_nge.components.ImageForm;
import com.example.if2210_tb2_nge.components.TextFieldForm;
import com.example.if2210_tb2_nge.controller.ItemController;
import com.example.if2210_tb2_nge.entity.Items;
import com.example.if2210_tb2_nge.repository.ItemsRepository;
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

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

public class ItemDetailPage {
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
    private Button saveBtn;
    @Getter
    private Button deleteBtn;
    @Getter
    private Button backBtn;
    private Boolean isEditMode;
    @Getter
    private Integer itemId;

    public ItemDetailPage() throws MalformedURLException {
        isEditMode = false;

        // page container
        pageContainer = new VBox();
        pageContainer.setAlignment(Pos.TOP_CENTER);

        // header
        header = new Label("ITEM DETAILS");
        Font fontTitle = Font.loadFont("file:src/assets/Montserrat-Regular.ttf", 50);
        header.setFont(fontTitle);
        header.setStyle("-fx-text-fill: #478660;");
        header.setPrefHeight(200);
        header.setAlignment(Pos.CENTER);
        pageContainer.getChildren().add(header);


        // images
        itemImage = new ImageForm("https://assets.pikiran-rakyat.com/crop/0x0:0x0/x/photo/2021/01/28/2114811954.jpg");
        itemImage.setIsDisable(true);
        itemImage.getImageContainer().prefHeight(300);
        pageContainer.getChildren().add(itemImage.getImageContainer());

        // name form
        horizontalTextFormContainer1 = new HBox();
        nameForm = new TextFieldForm("Name", "", 900);
        nameForm.setIsDisable(true);
        pageContainer.getChildren().add(horizontalTextFormContainer1);
        horizontalTextFormContainer1.setAlignment(Pos.CENTER);
        horizontalTextFormContainer1.getChildren().add(nameForm.getFormContainer());


        // category form
        horizontalTextFormContainer2 = new HBox();
        categoryForm = new TextFieldForm("Category", "", 900);
        categoryForm.setIsDisable(true);
        pageContainer.getChildren().add(horizontalTextFormContainer2);
        horizontalTextFormContainer2.setAlignment(Pos.CENTER);
        horizontalTextFormContainer2.getChildren().add(categoryForm.getFormContainer());

        // horizontal text form container
        horizontalTextFormContainer3 = new HBox();
        pageContainer.getChildren().add(horizontalTextFormContainer3);
        horizontalTextFormContainer3.setAlignment(Pos.CENTER);

        // price form
        priceForm = new TextFieldForm("Price", "", 300);
        priceForm.setIsDisable(true);
        horizontalTextFormContainer3.getChildren().add(priceForm.getFormContainer());

        // buy price form
        buyPriceForm = new TextFieldForm("Buy Price", "", 300);
        buyPriceForm.setIsDisable(true);
        horizontalTextFormContainer3.getChildren().add(buyPriceForm.getFormContainer());

        // stock form
        stockForm = new TextFieldForm("Stock", "", 300);
        stockForm.setIsDisable(true);
        horizontalTextFormContainer3.getChildren().add(stockForm.getFormContainer());

        // save edit button
        saveBtn = new Button("Edit");
        saveBtn.setStyle("-fx-background-color: #478660; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");
        saveBtn.setPrefWidth(400);
        saveBtn.setOnAction(e -> toggleEditMode());
        VBox.setMargin(saveBtn, new Insets(50, 0, 0, 0));
        pageContainer.getChildren().add(saveBtn);

        // delete button
        deleteBtn = new Button("Delete");
        deleteBtn.setStyle("-fx-background-color: #D86262; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");
        deleteBtn.setPrefWidth(400);
        VBox.setMargin(deleteBtn, new Insets(20, 0, 0, 0));
        pageContainer.getChildren().add(deleteBtn);

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

    public void toggleEditMode() {
        if (isEditMode) {
            isEditMode = false;
            saveBtn.setText(new String("Edit"));
            try {
                this.updateData();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            itemImage.getImageContainer().setOnMouseEntered(e -> {

            });
            itemImage.getImageContainer().setOnMouseExited(e -> {

            });
            nameForm.setIsDisable(true);
            categoryForm.setIsDisable(true);
            priceForm.setIsDisable(true);
            buyPriceForm.setIsDisable(true);
            stockForm.setIsDisable(true);
        } else {
            isEditMode = true;
            saveBtn.setText(new String("Save"));
            itemImage.getImageContainer().setOnMouseEntered(e -> {
                itemImage.setIsDisable(false);
            });
            itemImage.getImageContainer().setOnMouseExited(e -> {

                itemImage.setIsDisable(true);
            });

            nameForm.setIsDisable(false);
            categoryForm.setIsDisable(false);
            priceForm.setIsDisable(false);
            buyPriceForm.setIsDisable(false);
            stockForm.setIsDisable(false);
        }
    }

    public void updateData() throws Exception {
        ItemController.getItemInstance().setName(nameForm.getValue());
        ItemController.getItemInstance().setCategory(categoryForm.getValue());
        ItemController.getItemInstance().setPrice(Integer.parseInt(priceForm.getValue()));
        ItemController.getItemInstance().setBuyPrice(Integer.parseInt(buyPriceForm.getValue()));
        ItemController.getItemInstance().setStock(Integer.parseInt(stockForm.getValue()));
        ItemController.getItemInstance().setImage(itemImage.getImgUrl());
        ItemsRepository.updateItems(ItemController.getItemInstance());
    }

    public void readData(Integer id) throws Exception {
        itemId = id;
        List<Items> items = ItemsRepository.getItems();
        for (Items item : items) {
            if (item.getId() == id) {
                ItemController.setItemInstance(itemId);
                nameForm.setValue(item.getName());
                categoryForm.setValue(item.getCategory());
                priceForm.setValue(item.getPrice().toString());
                buyPriceForm.setValue(item.getBuyPrice().toString());
                stockForm.setValue(item.getStock().toString());
                break;
            }
        }
    }
}


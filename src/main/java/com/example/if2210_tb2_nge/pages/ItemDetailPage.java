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
import lombok.Setter;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

public class ItemDetailPage {
    @Getter
    private VBox pageContainer;
    private Label header;
    @Getter
    @Setter
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
        pageContainer.setAlignment(Pos.CENTER);

        // header
        header = new Label("ITEM DETAILS");
        Font fontTitle = Font.loadFont("file:src/assets/Montserrat-Bold.ttf", 50);
        header.setFont(fontTitle);
        header.setStyle("-fx-text-fill: #478660;");
        header.setPrefHeight(100);
        header.setAlignment(Pos.CENTER);
        pageContainer.getChildren().add(header);


        // images
        itemImage = new ImageForm(null);
        itemImage.setIsDisable(true);
        pageContainer.getChildren().add(itemImage.getImageContainer());

        // name form
        horizontalTextFormContainer1 = new HBox();
        nameForm = new TextFieldForm("Name", "", 940);
        nameForm.setIsDisable(true);
        pageContainer.getChildren().add(horizontalTextFormContainer1);
        horizontalTextFormContainer1.setAlignment(Pos.CENTER);
        horizontalTextFormContainer1.getChildren().add(nameForm.getFormContainer());


        // category form
        horizontalTextFormContainer2 = new HBox();
        categoryForm = new TextFieldForm("Category", "", 940);
        categoryForm.setIsDisable(true);
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
                try {
                    this.itemImage = new ImageForm(ItemController.getItemInstance().getImage());
                } catch (Exception e) {
                    this.itemImage = new ImageForm("data:image/jpeg;base64,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");
                }
                pageContainer.getChildren().clear();
                pageContainer.getChildren().addAll(header, this.itemImage.getImageContainer(), horizontalTextFormContainer1, horizontalTextFormContainer2, horizontalTextFormContainer3, saveBtn, deleteBtn, backBtn);
                System.out.println(this.itemImage.getImgUrl());
                break;
            }
        }
    }
}


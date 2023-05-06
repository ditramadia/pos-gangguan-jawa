package com.example.if2210_tb2_nge.pages;

import com.example.if2210_tb2_nge.components.ImageForm;
import com.example.if2210_tb2_nge.components.TextFieldForm;
import com.example.if2210_tb2_nge.controller.CustomerController;
import com.example.if2210_tb2_nge.controller.ItemController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.Getter;

import java.util.Map;

public class CustomerDetailPage {
    @Getter
    private VBox pageContainer;
    private Label header;
    @Getter
    private ImageForm itemImage;
    @Getter
    private TextFieldForm nameForm;
    @Getter
    private TextFieldForm categoryForm;
    private HBox horizontalTextFormContainer;
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

    public CustomerDetailPage() {
        isEditMode = false;

        // page container
        pageContainer = new VBox();

        // header
        header = new Label("ITEM DETAILS");
        pageContainer.getChildren().add(header);

        // images
        itemImage = new ImageForm("https://assets.pikiran-rakyat.com/crop/0x0:0x0/x/photo/2021/01/28/2114811954.jpg");
        itemImage.setIsDisable(true);
        pageContainer.getChildren().add(itemImage.getImageContainer());

        // name form
        nameForm = new TextFieldForm("Name", "", 200);
        nameForm.setIsDisable(true);
        pageContainer.getChildren().add(nameForm.getFormContainer());

        // category form
        categoryForm = new TextFieldForm("Category", "", 200);
        categoryForm.setIsDisable(true);
        pageContainer.getChildren().add(categoryForm.getFormContainer());

        // horizontal text form container
        horizontalTextFormContainer = new HBox();
        pageContainer.getChildren().add(horizontalTextFormContainer);

        // price form
        priceForm = new TextFieldForm("Price", "", 266);
        priceForm.setIsDisable(true);
        horizontalTextFormContainer.getChildren().add(priceForm.getFormContainer());

        // buy price form
        buyPriceForm = new TextFieldForm("Buy Price", "", 266);
        buyPriceForm.setIsDisable(true);
        horizontalTextFormContainer.getChildren().add(buyPriceForm.getFormContainer());

        // stock form
        stockForm = new TextFieldForm("Stock", "", 266);
        stockForm.setIsDisable(true);
        horizontalTextFormContainer.getChildren().add(stockForm.getFormContainer());

        // save edit button
        saveBtn = new Button("Edit");
        saveBtn.setOnAction(e -> toggleEditMode());
        pageContainer.getChildren().add(saveBtn);

        // delete button
        deleteBtn = new Button("Delete");
        pageContainer.getChildren().add(deleteBtn);

        // back button
        backBtn = new Button("Back");
        pageContainer.getChildren().add(backBtn);
    }

    public void readData(Integer id) throws Exception {
        Map<String, Object> item = CustomerController.getCustomer(id);
        this.itemId = id;
        nameForm.setValue(item.get("name").toString());
        priceForm.setValue(item.get("noTelp").toString());
        buyPriceForm.setValue(item.get("active").toString());
        stockForm.setValue(item.get("vip").toString());

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
        ItemController.updateItems(itemId, nameForm.getValue(), Integer.parseInt(priceForm.getValue()), Integer.parseInt(buyPriceForm.getValue()), Integer.parseInt(stockForm.getValue()), categoryForm.getValue());
    }
}


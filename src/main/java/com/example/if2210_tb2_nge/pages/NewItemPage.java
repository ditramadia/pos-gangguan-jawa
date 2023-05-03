package com.example.if2210_tb2_nge.pages;

import com.example.if2210_tb2_nge.components.ImageForm;
import com.example.if2210_tb2_nge.components.TextFieldForm;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.Getter;

public class NewItemPage {
    @Getter
    private VBox pageContainer;
    private Label header;
    private ImageForm itemImage;
    private TextFieldForm nameForm;
    private TextFieldForm categoryForm;
    private HBox horizontalTextFormContainer;
    private TextFieldForm priceForm;
    private TextFieldForm buyPriceForm;
    private TextFieldForm stockForm;
    private Button saveBtn;
    @Getter
    private Button backBtn;

    public NewItemPage() {
        // page container
        pageContainer = new VBox();

        // header
        header = new Label("NEW ITEM");
        pageContainer.getChildren().add(header);

        // images
        itemImage = new ImageForm("https://assets.pikiran-rakyat.com/crop/0x0:0x0/x/photo/2021/01/28/2114811954.jpg");
        pageContainer.getChildren().add(itemImage.getImageContainer());

        // name form
        nameForm = new TextFieldForm("Name", "", 200);
        pageContainer.getChildren().add(nameForm.getFormContainer());

        // category form
        categoryForm = new TextFieldForm("Category", "", 200);
        pageContainer.getChildren().add(categoryForm.getFormContainer());

        // horizontal text form container
        horizontalTextFormContainer = new HBox();
        pageContainer.getChildren().add(horizontalTextFormContainer);

        // price form
        priceForm = new TextFieldForm("Price", "", 266);
        horizontalTextFormContainer.getChildren().add(priceForm.getFormContainer());

        // buy price form
        buyPriceForm = new TextFieldForm("Buy Price", "", 266);
        horizontalTextFormContainer.getChildren().add(buyPriceForm.getFormContainer());

        // stock form
        stockForm = new TextFieldForm("Stock", "", 266);
        horizontalTextFormContainer.getChildren().add(stockForm.getFormContainer());

        // save button
        saveBtn = new Button("Save");
        pageContainer.getChildren().add(saveBtn);

        // back button
        backBtn = new Button("Back");
        pageContainer.getChildren().add(backBtn);
    }

    public void resetPage() {
        nameForm.setValue("");
        categoryForm.setValue("");
        priceForm.setValue("");
        buyPriceForm.setValue("");
        stockForm.setValue("");
    }
}

package com.example.if2210_tb2_nge.pages;

import com.example.if2210_tb2_nge.components.ConfirmationBox;
import com.example.if2210_tb2_nge.components.ImageForm;
import com.example.if2210_tb2_nge.components.TextFieldForm;
import com.example.if2210_tb2_nge.controller.CustomerController;
import com.example.if2210_tb2_nge.entity.Customers;
import com.example.if2210_tb2_nge.entity.Members;
import com.example.if2210_tb2_nge.repository.CustomersRepository;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

public class RegistrationPage extends VBox {
    @Getter
    private Label header;
    @Getter
    private TextFieldForm nameForm;
    @Getter
    private TextFieldForm noTelpForm;
    private HBox horizontalTextFormContainer1;
    private HBox horizontalTextFormContainer2;
    private HBox horizontalTextFormContainer3;
    @Getter
    private TextFieldForm pointsForm;
    @Getter
    private CheckBox vipForm;
    private Button saveBtn;
    @Getter
    private Button backBtn;
    @Getter
    private Integer customerId;

    public RegistrationPage() {
        // page container
        setAlignment(Pos.CENTER);

        // header
        header = new Label("NEW MEMBER");
        Font fontTitle = Font.loadFont("file:src/assets/Montserrat-Bold.ttf", 50);
        header.setFont(fontTitle);
        header.setStyle("-fx-text-fill: #478660;");
        header.setPrefHeight(100);
        header.setAlignment(Pos.CENTER);
        getChildren().add(header);

        // name form
        horizontalTextFormContainer1 = new HBox();
        nameForm = new TextFieldForm("Name", "", 940);
        getChildren().add(horizontalTextFormContainer1);
        horizontalTextFormContainer1.setAlignment(Pos.CENTER);
        horizontalTextFormContainer1.getChildren().add(nameForm.getFormContainer());

        //no telp form
        noTelpForm = new TextFieldForm("Phone Number", "", 940);
        horizontalTextFormContainer2 = new HBox();
        getChildren().add(horizontalTextFormContainer2);
        horizontalTextFormContainer2.setAlignment(Pos.CENTER);
        horizontalTextFormContainer2.getChildren().add(noTelpForm.getFormContainer());

        // vip form
        horizontalTextFormContainer3 = new HBox();
        vipForm = new CheckBox("VIP");
        vipForm.setFont(Font.loadFont("file:src/assets/Montserrat-Regular.ttf", 20));
        getChildren().add(horizontalTextFormContainer3);
        HBox.setMargin(vipForm, new Insets(40, 0, 0, 0));
        horizontalTextFormContainer3.setAlignment(Pos.CENTER);
        horizontalTextFormContainer3.getChildren().add(vipForm);

        // save button
        saveBtn = new Button("Register");
        saveBtn.setStyle("-fx-background-color: #478660; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");
        saveBtn.setPrefWidth(400);
        saveBtn.setOnAction(e -> this.save());
        VBox.setMargin(saveBtn, new Insets(50, 0, 0, 0));
        getChildren().add(saveBtn);

        // back button
        backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: #8C7466; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");
        backBtn.setPrefWidth(400);
        VBox.setMargin(backBtn, new Insets(20, 0, 0, 0));
        getChildren().add(backBtn);

        resetPage();
    }

    public void resetPage() {
        nameForm.setValue("");
        noTelpForm.setValue("");
        vipForm.setSelected(false);
    }

    public void save() {
        Members newMember = new Members(CustomersRepository.getLastIdCustomers(), nameForm.getValue(), noTelpForm.getValue(), 0, vipForm.isSelected(), true);
        try {
            CustomersRepository.addCustomer(newMember);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (Customers customer : CustomersRepository.getCustomers()) {
            System.out.println(customer.getName());
        }
    }
}
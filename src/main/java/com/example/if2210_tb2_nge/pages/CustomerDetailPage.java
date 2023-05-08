package com.example.if2210_tb2_nge.pages;

import com.example.if2210_tb2_nge.components.ConfirmationBox;
import com.example.if2210_tb2_nge.components.TextFieldForm;
import com.example.if2210_tb2_nge.controller.CustomerController;
import com.example.if2210_tb2_nge.entity.Members;
import com.example.if2210_tb2_nge.repository.CustomersRepository;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

public class CustomerDetailPage {
    @Getter
    private VBox pageContainer;
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
    private Button deactivateBtn;
    @Getter
    private Button historyBtn;
    @Getter
    private Button backBtn;
    private Boolean isEditMode;
    @Getter
    private Integer customerId;
    private ConfirmationBox confirmationBox;

    public CustomerDetailPage() {
        isEditMode = false;

        // page container
        pageContainer = new VBox();
        pageContainer.setAlignment(Pos.CENTER);

        // header
        header = new Label("CUSTOMER DETAILS");
        Font fontTitle = Font.loadFont("file:src/assets/Montserrat-Bold.ttf", 50);
        header.setFont(fontTitle);
        header.setStyle("-fx-text-fill: #478660;");
        header.setPrefHeight(100);
        header.setAlignment(Pos.CENTER);
        pageContainer.getChildren().add(header);

        // name form
        horizontalTextFormContainer1 = new HBox();
        nameForm = new TextFieldForm("Name", "", 940);
        nameForm.setIsDisable(true);
        pageContainer.getChildren().add(horizontalTextFormContainer1);
        horizontalTextFormContainer1.setAlignment(Pos.CENTER);
        horizontalTextFormContainer1.getChildren().add(nameForm.getFormContainer());

        // horizontal text form container
        horizontalTextFormContainer2 = new HBox();
        pageContainer.getChildren().add(horizontalTextFormContainer2);
        horizontalTextFormContainer2.setAlignment(Pos.CENTER);
        horizontalTextFormContainer2.setSpacing(20);

        //no telp form
        noTelpForm = new TextFieldForm("Phone Number", "", 460);
        noTelpForm.setIsDisable(true);
        horizontalTextFormContainer2.getChildren().add(noTelpForm.getFormContainer());

        // points form
        pointsForm = new TextFieldForm("Points", "", 460);
        pointsForm.setIsDisable(true);
        horizontalTextFormContainer2.getChildren().add(pointsForm.getFormContainer());

        // vip form
        horizontalTextFormContainer3 = new HBox();
        vipForm = new CheckBox("VIP");
        vipForm.setFont(Font.loadFont("file:src/assets/Montserrat-Regular.ttf", 20));
        vipForm.setDisable(true);
        pageContainer.getChildren().add(horizontalTextFormContainer3);
        HBox.setMargin(vipForm, new Insets(40, 0, 0, 0));
        horizontalTextFormContainer3.setAlignment(Pos.CENTER);
        horizontalTextFormContainer3.getChildren().add(vipForm);

        // save edit button
        saveBtn = new Button("Edit");
        saveBtn.setStyle("-fx-background-color: #478660; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");
        saveBtn.setPrefWidth(400);
        saveBtn.setOnAction(e -> toggleEditMode());
        VBox.setMargin(saveBtn, new Insets(50, 0, 0, 0));
        pageContainer.getChildren().add(saveBtn);

        // deactivate button
        deactivateBtn = new Button("");
        deactivateBtn.setPrefWidth(400);
        VBox.setMargin(deactivateBtn, new Insets(20, 0, 0, 0));
        deactivateBtn.setOnAction(e -> {
            try {
                if (CustomerController.getCustomerInstance().getActive()) {
                    confirmationBox = new ConfirmationBox(2);
                } else {
                    confirmationBox = new ConfirmationBox(3);
                }
                Optional<ButtonType> result = confirmationBox.getAlertBox().showAndWait();
                if (result.get() == ButtonType.OK){
                    if (CustomerController.getCustomerInstance().getActive()) {
                        CustomerController.getCustomerInstance().setActive(false);
                        deactivateBtn.setText(new String("Activate"));
                        deactivateBtn.setStyle("-fx-background-color: #478660; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");
                    } else {
                        CustomerController.getCustomerInstance().setActive(true);
                        deactivateBtn.setText(new String("Deactivate"));
                        deactivateBtn.setStyle("-fx-background-color: #D86262; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");
                    }

                    try {
                        this.updateData();
                    } catch (Exception err) {
                        throw new RuntimeException(err);
                    }
                    CustomersRepository.updateCustomer(CustomerController.getCustomerInstance());
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        pageContainer.getChildren().add(deactivateBtn);

        // history button
        historyBtn = new Button("History");
        historyBtn.setStyle("-fx-background-color: #8C7466; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");
        historyBtn.setPrefWidth(400);
        VBox.setMargin(historyBtn, new Insets(20, 0, 0, 0));
        pageContainer.getChildren().add(historyBtn);

        // back button
        backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: #8C7466; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");
        backBtn.setPrefWidth(400);
        VBox.setMargin(backBtn, new Insets(20, 0, 0, 0));
        pageContainer.getChildren().add(backBtn);
    }

    public void resetPage() {
        nameForm.setValue("");
        noTelpForm.setValue("");
        pointsForm.setValue("");
        vipForm.setSelected(false);
    }

    public void toggleEditMode() {
        if (isEditMode) {
            isEditMode = false;
            saveBtn.setText(new String("Edit"));
            try {
                System.out.println("update data");
                this.updateData();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            nameForm.setIsDisable(true);
            noTelpForm.setIsDisable(true);
            pointsForm.setIsDisable(true);
            vipForm.setDisable(true);
        } else {
            isEditMode = true;
            saveBtn.setText(new String("Save"));
            nameForm.setIsDisable(false);
            noTelpForm.setIsDisable(false);
            pointsForm.setIsDisable(false);
            vipForm.setDisable(false);
        }
    }

    public void updateData() throws Exception {
        CustomerController.getCustomerInstance().setName(nameForm.getValue());
        CustomerController.getCustomerInstance().setNoTelp(noTelpForm.getValue());
        CustomerController.getCustomerInstance().setPoints(Integer.parseInt(pointsForm.getValue()));
        CustomerController.getCustomerInstance().setVip(vipForm.isSelected());
        CustomersRepository.updateCustomer(CustomerController.getCustomerInstance());
    }

    public void readData(Integer id) throws Exception {
        customerId = id;
        List<Members> customers = CustomersRepository.getMembersOnly();
        for (Members customer : customers) {
            if (customer.getId() == id) {
                CustomerController.setCustomerInstance(customerId);
                nameForm.setValue(customer.getName());
                noTelpForm.setValue(customer.getNoTelp());
                pointsForm.setValue(customer.getPoints().toString());
                if (customer.getVip()) {
                    vipForm.setSelected(true);
                } else {
                    vipForm.setSelected(false);
                }
                if (customer.getActive()) {
                    deactivateBtn.setText(new String("Deactivate"));
                    deactivateBtn.setStyle("-fx-background-color: #D86262; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");
                } else {
                    deactivateBtn.setText(new String("Activate"));
                    deactivateBtn.setStyle("-fx-background-color: #478660; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");
                }
                break;
            }
        }
    }
}


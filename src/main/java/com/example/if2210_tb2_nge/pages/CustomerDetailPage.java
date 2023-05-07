package com.example.if2210_tb2_nge.pages;

import com.example.if2210_tb2_nge.components.ConfirmationBox;
import com.example.if2210_tb2_nge.components.TextFieldForm;
import com.example.if2210_tb2_nge.controller.CustomerController;
import com.example.if2210_tb2_nge.entity.Members;
import com.example.if2210_tb2_nge.repository.CustomersRepository;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    private HBox horizontalTextFormContainer;
    @Getter
    private TextFieldForm pointsForm;
    @Getter
    private TextFieldForm vipForm;
    private Button saveBtn;
    @Getter
    private Button deactivateBtn;
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

        // header
        header = new Label("CUSTOMER DETAILS");
        pageContainer.getChildren().add(header);

        // name form
        nameForm = new TextFieldForm("Name", "", 200);
        nameForm.setIsDisable(true);
        pageContainer.getChildren().add(nameForm.getFormContainer());

        // no telp form
        noTelpForm = new TextFieldForm("Phone Number", "", 200);
        noTelpForm.setIsDisable(true);
        pageContainer.getChildren().add(noTelpForm.getFormContainer());

        // horizontal text form container
        horizontalTextFormContainer = new HBox();
        pageContainer.getChildren().add(horizontalTextFormContainer);

        // points form
        pointsForm = new TextFieldForm("Points", "", 266);
        pointsForm.setIsDisable(true);
        horizontalTextFormContainer.getChildren().add(pointsForm.getFormContainer());

        // vip form
        vipForm = new TextFieldForm("VIP", "", 266);
        vipForm.setIsDisable(true);
        horizontalTextFormContainer.getChildren().add(vipForm.getFormContainer());

        // save edit button
        saveBtn = new Button("Edit");
        saveBtn.setOnAction(e -> toggleEditMode());
        pageContainer.getChildren().add(saveBtn);

        // deactivate button
        deactivateBtn = new Button("Deactivate");
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
                    } else {
                        CustomerController.getCustomerInstance().setActive(false);
                        deactivateBtn.setText(new String("Deactivate"));
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

        // back button
        backBtn = new Button("Back");
        pageContainer.getChildren().add(backBtn);
    }

    public void resetPage() {
        nameForm.setValue("");
        noTelpForm.setValue("");
        pointsForm.setValue("");
        vipForm.setValue("");
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
            vipForm.setIsDisable(true);
        } else {
            isEditMode = true;
            saveBtn.setText(new String("Save"));
            nameForm.setIsDisable(false);
            noTelpForm.setIsDisable(false);
            pointsForm.setIsDisable(false);
            vipForm.setIsDisable(false);
        }
    }

    public void updateData() throws Exception {
        CustomerController.getCustomerInstance().setName(nameForm.getValue());
        CustomerController.getCustomerInstance().setNoTelp(noTelpForm.getValue());
        CustomerController.getCustomerInstance().setPoints(Integer.parseInt(pointsForm.getValue()));
        CustomerController.getCustomerInstance().setVip(Boolean.parseBoolean(vipForm.getValue()));
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
                vipForm.setValue(customer.getVip().toString());
                break;
            }
        }
    }
}


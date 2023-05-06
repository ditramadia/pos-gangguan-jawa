package com.example.if2210_tb2_nge.components;

import javafx.scene.control.Alert;
import lombok.Getter;

public class ConfirmationBox {
    @Getter
    private Alert alertBox;
    @Getter
    private Alert errorBox;


    public ConfirmationBox(int type){
        alertBox = new Alert(Alert.AlertType.CONFIRMATION);
        errorBox = new Alert(Alert.AlertType.ERROR);
        if (type == 1){
            alertBox.setTitle("Delete Confirmation");
            alertBox.setContentText("Do you want to delete this items?");
            errorBox.setTitle("Save Failed!!!");
            errorBox.setContentText("Please fill all the attribute");
        } else if (type == 2) {
            alertBox.setTitle("Deactivation Confirmation");
            alertBox.setContentText("Do you want to deactivate this customer?");
            errorBox.setTitle("Save Failed!!!");
            errorBox.setContentText("Please fill all the attribute");
        } else if (type == 3) {
            alertBox.setTitle("Activation Confirmation");
            alertBox.setContentText("Do you want to activate this customer?");
            errorBox.setTitle("Save Failed!!!");
            errorBox.setContentText("Please fill all the attribute");
        }

    }
}

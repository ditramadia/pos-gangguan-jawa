package com.example.if2210_tb2_nge.components;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import lombok.Getter;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

public class CustomerSelectionCard {
    @Getter
    private VBox cardContainer;
    private Label title;
    @Getter
    private TextField customerSelection;
    private AutoCompletionBinding<String> autoCompletionBinding;
    private String[] suggestions = {"John Doe", "Jane Smith", "Bob Johnson"};

    public CustomerSelectionCard () {
        cardContainer = new VBox();
        title = new Label("CUSTOMER");

        customerSelection = new TextField();

        // Set up the auto-completion feature
        autoCompletionBinding = TextFields.bindAutoCompletion(customerSelection, suggestions);
        autoCompletionBinding.setPrefWidth(customerSelection.getPrefWidth());

        cardContainer.getChildren().addAll(title, customerSelection);
    }
}

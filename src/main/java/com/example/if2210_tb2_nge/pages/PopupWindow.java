package com.example.if2210_tb2_nge.pages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;

import java.io.File;

public class PopupWindow {
    @Getter
    private TextField folderField;
    @Getter
    private ComboBox<String> comboBox;


    public void display(String message) {
        Stage popupWindow = new Stage();
        popupWindow.initStyle(StageStyle.UTILITY);
        popupWindow.initModality(Modality.APPLICATION_MODAL);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> popupWindow.close());

        Label title = new Label("DATA STORE");
        title.setFont(new Font(50));

        HBox folderSelection = new HBox();
        folderSelection.setAlignment(Pos.CENTER);
        folderField = new TextField();
        folderField.setDisable(true);
        Button folderButton = new Button("Select Folder");

        folderButton.setOnAction(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(popupWindow);
            if (selectedDirectory != null) {
                folderField.setText(selectedDirectory.getAbsolutePath());
            }
        });

        folderSelection.getChildren().addAll(folderField,folderButton);
        folderSelection.setPadding(new Insets(10));

        comboBox = new ComboBox<>();
        comboBox.getItems().addAll("JSON", "XML", "OBJ");
        comboBox.setValue("JSON");

        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        VBox.setMargin(folderSelection, new Insets(50, 0,0,0));
        VBox.setMargin(comboBox, new Insets(30, 0,0,0));
        VBox.setMargin(closeButton, new Insets(30, 0,0,0));
        layout.getChildren().addAll(title,folderSelection,comboBox, closeButton);

        Scene scene = new Scene(layout, 700, 400);
        popupWindow.setScene(scene);
        popupWindow.showAndWait();
    }
}

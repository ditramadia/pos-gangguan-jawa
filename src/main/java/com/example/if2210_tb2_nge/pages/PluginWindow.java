package com.example.if2210_tb2_nge.pages;

import com.example.if2210_tb2_nge.plugin.PluginLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;

import java.io.File;

public class PluginWindow extends PopupWindow {

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

        Label title = new Label("PLUGIN");
        title.setFont(new Font(50));

        HBox folderSelection = new HBox();
        folderField = new TextField();
        folderField.setDisable(true);
        Button folderButton = new Button("Select File");

        folderButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(popupWindow);
            if (selectedFile != null) {
                folderField.setText(selectedFile.getAbsolutePath());
            }
        });

        folderSelection.getChildren().addAll(folderField,folderButton);
        folderSelection.setPadding(new Insets(10));

        Button apply = new Button("Apply");
        apply.setOnAction(event -> {
            if (comboBox.getValue() != null && folderField.getText() != null) {
                try {
                    PluginLoader.loadJarFile(folderField.getText());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                popupWindow.close();
            }
        });

        VBox layout = new VBox();
        layout.getChildren().addAll(title,folderSelection,apply, closeButton);

        Scene scene = new Scene(layout, 700, 400);
        popupWindow.setScene(scene);
        popupWindow.showAndWait();
    }
}

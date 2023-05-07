package com.example.if2210_tb2_nge.components;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import javafx.scene.control.Control;
import lombok.Getter;

public class SearchBar extends Control {
    @Getter
    private HBox searchBarContainer;
    @Getter
    private TextField searchField;

    public SearchBar() {
        // container
        searchBarContainer = new HBox();
        searchBarContainer.setPrefWidth(800);
        searchBarContainer.setAlignment(Pos.CENTER);

        // search placeholder
        searchField = new TextField();
        searchField.setPromptText("Search");
        searchField.setPrefWidth(800);
        searchField.setStyle("-fx-background-radius: 20; -fx-text-fill: #83695A; -fx-font-size: 20; -fx-font-weight: bold; -fx-border-color: #83695A; -fx-border-radius: 20; -fx-border-width: 2; -fx-padding: 10;");
        searchBarContainer.getChildren().add(searchField);
    }
}

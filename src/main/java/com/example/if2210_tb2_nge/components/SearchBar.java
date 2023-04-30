package com.example.if2210_tb2_nge.components;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import javafx.scene.control.Control;
import lombok.Getter;

public class SearchBar extends Control {
    @Getter
    private HBox searchBarContainer;
    private TextField searchField;

    public SearchBar() {
        // container
        searchBarContainer = new HBox();

        // search placeholder
        searchField = new TextField();
        searchField.setPromptText("Search");
        searchBarContainer.getChildren().add(searchField);
    }
}

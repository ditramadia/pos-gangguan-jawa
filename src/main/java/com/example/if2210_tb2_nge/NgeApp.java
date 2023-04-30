package com.example.if2210_tb2_nge;

import com.example.if2210_tb2_nge.pages.HomePage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.IOException;

public class NgeApp extends Application implements EventHandler<ActionEvent> {
    HomePage homePage;
    TabPane tabPane;

    @Override
    public void start(Stage stage) throws IOException {
        // land on homepage
        homePage = new HomePage();

        // app
        stage.setTitle("Gangguan Jawa");
        BorderPane root = new BorderPane();

        // Menu Bar
        MenuBar menuBar = new MenuBar();

        Menu file = new Menu("File");
        MenuItem importDb = new MenuItem("Import Database");
        MenuItem exportDb = new MenuItem("Export Database");
        MenuItem exit = new MenuItem("Exit");
        file.getItems().addAll(importDb,exportDb,exit);

        Menu plugin = new Menu ("Plugin");
        MenuItem importPlugin = new MenuItem("Import Plugin");
        MenuItem removePlugin = new MenuItem("Remove Plugin");
        plugin.getItems().addAll(importPlugin, removePlugin);

        Menu setting = new Menu("Setting");
        MenuItem datastore = new MenuItem("Data Store");
        setting.getItems().addAll(datastore);

        Menu help = new Menu("Help");
        MenuItem about = new MenuItem("About");
        MenuItem author = new MenuItem("Author");
        MenuItem howToUse = new MenuItem("How To Use");
        help.getItems().addAll(howToUse, about, author);

        menuBar.setUseSystemMenuBar(true);
        menuBar.getMenus().addAll(file,plugin, setting, help);

        // Tab Panel
        tabPane = new TabPane();
        Tab tab1 = homePage.getTab();
        tab1.setClosable(false);
        tabPane.getTabs().add(tab1);

        homePage.getHomeNavBtn().setOnAction(this);
        homePage.getCustomerNavBtn().setOnAction(actionEvent -> handleHomeButton());

        root.setTop(menuBar);
        root.setCenter(tabPane);
        Scene scene = new Scene(root, 1920, 1024);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void handle(ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof Button) {
            Button button = (Button) actionEvent.getSource();
            String buttonText = button.getText();
            if (buttonText.equals("Home")) {
                HomePage newHomePage = new HomePage();
                newHomePage.getHomeNavBtn().setOnAction(this);
                tabPane.getTabs().add(newHomePage.getTab());
            }
        }
    }

    private void handleHomeButton() {
        handle(new ActionEvent(homePage.getHomeNavBtn(), null));
    }

}


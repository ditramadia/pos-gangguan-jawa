package com.example.if2210_tb2_nge;

import com.example.if2210_tb2_nge.pages.HomePage;
import com.example.if2210_tb2_nge.pages.MenuPage;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class NgeApp extends Application implements EventHandler<ActionEvent> {
    HomePage homePage;
    TabPane tabPane;

    @Override
    public void start(Stage stage) throws IOException {
        // land on homepage
        homePage = new HomePage();
        homePage.getHomeNavBtn().setOnAction(this);
        homePage.getInventoryNavBtn().setOnAction(this);

        // app
        stage.setTitle("Gangguan Jawa");
        BorderPane root = new BorderPane();
//        root.setStyle("-fx-background-color: #8C7466");

        // Menu Bar
        MenuBar menuBar = new MenuBar();
        menuBar.setStyle("-fx-background-color: #8C7466");

        Menu file = new Menu("File");
//        file.setStyle("-fx-background-color: black; -fx-text-fill: white;");
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
//        tabPane.setStyle("-fx-tab-min-width: 100; -fx-tab-max-width: 100; -fx-tab-min-height: 30; -fx-tab-max-height: 30; -fx-background-color: #FFFFFF; -fx-tab-background-color: #CCCCCC; -fx-tab-text-color: white;");

        File cssFile = new File("src/main/java/com/example/if2210_tb2_nge/style/style.css");
        String cssUrl = cssFile.toURI().toURL().toExternalForm();

//        tabPane.setStyle("-fx-background-color: #D7CDC7");
        Tab tab1 = homePage.getTab();
        tab1.selectedProperty().addListener(new ChangeListener<Boolean>(){

            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                if (t1.booleanValue()) {
                    // it would be better to use FadeTransitionBuilder here...
                    FadeTransition ft = new FadeTransition();
                    ft.setNode(tab1.getContent());
                    ft.setDuration(Duration.millis(2000));
                    ft.setFromValue(0.1);
                    ft.setToValue(1.0);
                    ft.setCycleCount(1);
                    ft.play();
                }
            }
        });
//        tab1.setStyle("-fx-background-color: #83695A; -fx-text-fill: white;");
        tab1.setClosable(false);
        tabPane.getTabs().add(tab1);

        root.setTop(menuBar);
        root.setCenter(tabPane);
        Scene scene = new Scene(root, 1920, 1024);
        root.getStylesheets().add(cssUrl);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void handle(ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof Button)
        {
            Button button = (Button) actionEvent.getSource();
            if (button.getText() == "Home") {
                HomePage newHomePage = new HomePage();
                newHomePage.getHomeNavBtn().setOnAction(this);
                newHomePage.getInventoryNavBtn().setOnAction(this);
                tabPane.getTabs().add(newHomePage.getTab());
            } else if (button.getText() == "Inventory") {
                MenuPage newMenuPage = null;
                try {
                    newMenuPage = new MenuPage();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                tabPane.getTabs().add(newMenuPage.getTab());
            }
        }
    }

}


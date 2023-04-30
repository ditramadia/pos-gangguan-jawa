package com.example.if2210_tb2_nge;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.embed.swing.SwingNode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.PopupWindow;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.io.IOException;
import javafx.animation.*;
import javafx.util.*;
import javafx.util.Duration;
import java.time.*;
import java.time.format.DateTimeFormatter;
import javafx.geometry.*;

public class HelloApplication extends Application {
    Button button;
    Clock clock;
    HomePage homePage;
    MenuPage menu;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("JAVAAAAAA");
        BorderPane root = new BorderPane();

        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File");        Menu settings = new Menu ("Settings");


        menuBar.setUseSystemMenuBar(true);

        menuBar.getMenus().addAll(file,settings);

        MenuItem item1 = new MenuItem("Open");
        MenuItem item2 = new MenuItem("Save");
        MenuItem item3 = new MenuItem("Exit");

        file.getItems().addAll(item1,item2,item3);
        root.setTop(menuBar);

        TabPane tabPane = new TabPane();
        Tab tab1 = new Tab("Tab 1");
        BorderPane layouttab1 = new BorderPane();
//
        homePage = new HomePage();
        menu = new MenuPage();
//
//
        tab1.setContent(layouttab1);
//
        tab1.setClosable(false);
//
        VBox sideMenu = new VBox();
        Button button1 = new Button("Button 1");
        Button button2 = new Button("Button 2");
        Button button3 = new Button("Button 3");
        sideMenu.getChildren().addAll(button1,button2,button3);
        sideMenu.setStyle("-fx-background-color: #ADD8E6;");
        sideMenu.setAlignment(Pos.TOP_LEFT);
        sideMenu.setPrefWidth(200);
        layouttab1.setLeft(sideMenu);
        StackPane mulscreen = new StackPane();

        mulscreen.getChildren().add(homePage.getlayout());
        mulscreen.getChildren().add(menu.getlayout());
        button1.setOnAction(e -> {
            mulscreen.getChildren().get(0).setVisible(true);
            mulscreen.getChildren().get(1).setVisible(false);
        });
        button2.setOnAction(e -> {
            mulscreen.getChildren().get(1).setVisible(true);
            mulscreen.getChildren().get(0).setVisible(false);
        });
        layouttab1.setCenter(mulscreen);
        mulscreen.getChildren().get(1).setVisible(false);
//
//
        tabPane.getTabs().add(tab1);



        Tab newTab = new Tab("+");
        tabPane.getTabs().add(newTab);

        newTab.setOnSelectionChanged(e-> {
            Tab tab = new Tab("New Tab");
            tabPane.getTabs().add(tabPane.getTabs().size()-1,tab);
            tabPane.getSelectionModel().select(tabPane.getTabs().size()-2);
            System.out.println(tabPane.getTabs().size()-1);
        });

        root.setCenter(tabPane);
        Scene scene = new Scene(root, 300, 250);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}


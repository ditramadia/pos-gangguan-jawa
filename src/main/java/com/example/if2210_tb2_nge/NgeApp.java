package com.example.if2210_tb2_nge;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;

public class NgeApp extends Application implements EventHandler<ActionEvent> {
    HomePage homePage;
    TabPane tabPane;

    @Override
    public void start(Stage stage) throws IOException {
        homePage = new HomePage();
        // app
        stage.setTitle("Gangguan Java");
        BorderPane root = new BorderPane();

        // Menu Bar
        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File");
        Menu settings = new Menu ("Settings");

        menuBar.setUseSystemMenuBar(true);

        menuBar.getMenus().addAll(file,settings);

        MenuItem item1 = new MenuItem("Open");
        MenuItem item2 = new MenuItem("Save");
        MenuItem item3 = new MenuItem("Exit");

        file.getItems().addAll(item1,item2,item3);

        // add Tab


        // Tab Panel
        tabPane = new TabPane();
        Tab tab1 = homePage.getTab();
        tab1.setClosable(false);
        tabPane.getTabs().add(tab1);
//        Tab tab2 = new Tab("Nwwwww");
//        tabPane.getTabs().add(tab2);

        homePage.getButton1().setOnAction(this);

        root.setTop(menuBar);
        root.setCenter(tabPane);
        Scene scene = new Scene(root, 1920, 1024);
        stage.setScene(scene);
        stage.show();
    }




    public static void main(String[] args) {
        launch();
    }

//    @Override
    public void handle(ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.homePage.getButton1()){
            HomePage temp = new HomePage();
            tabPane.getTabs().add(temp.getTab());
        }

    }
}


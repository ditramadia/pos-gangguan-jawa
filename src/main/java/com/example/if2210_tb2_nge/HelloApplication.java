package com.example.if2210_tb2_nge;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.embed.swing.SwingNode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.PopupWindow;
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
    Label texttt;
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        stage.setTitle("Hello!");
        BorderPane root = new BorderPane();

        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File");
        Menu settings = new Menu ("Settings");


        menuBar.setUseSystemMenuBar(true);

        menuBar.getMenus().addAll(file,settings);

        MenuItem item1 = new MenuItem("Open");
        MenuItem item2 = new MenuItem("Save");
        MenuItem item3 = new MenuItem("Exit");

        file.getItems().addAll(item1,item2,item3);
        root.setTop(menuBar);

        TabPane tabPane = new TabPane();
        Tab tab1 = new Tab("Tab 1");
        VBox panetab = new VBox();
        panetab.setAlignment(Pos.TOP_CENTER);
        Label kel = new Label("NGE");
        Label clockLabel = new Label();

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            clockLabel.setText(currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        }), new KeyFrame(Duration.seconds(1)));

        panetab.getChildren().add(kel);
        panetab.getChildren().add(clockLabel);
        tab1.setContent(panetab);

        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
        tab1.setClosable(false);

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
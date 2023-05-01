package com.example.if2210_tb2_nge.pages;

import com.example.if2210_tb2_nge.components.Clock;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.util.Duration;
import javafx.scene.control.Tab;
import javafx.scene.control.Button;
import lombok.Getter;

public class HomePage {
    @Getter
    private Tab tab;
    private VBox container;
    private HBox logoTime;
    private VBox timeDate;
    @Getter
    private VBox sideMenu;
    @Getter
    private Button homeNavBtn;
    @Getter
    private Button transactionNavBtn;
    @Getter
    private Button customerNavBtn;
    @Getter
    private Button inventoryNavBtn;
    private BorderPane layout;
    private Clock clock;
    private boolean isSideMenuOpen = false;

    public HomePage(){
        // tab
        tab = new Tab("Home");
        tab.setStyle("-fx-background-radius: 10 10 0 0;");
        TabPane tabPane = new TabPane();
        tabPane.getTabs().add(tab);


        // layout
        layout = new BorderPane();
        container = new VBox();
        container.setAlignment(Pos.CENTER);

        logoTime = new HBox();
        timeDate = new VBox();
        logoTime.setPrefWidth(500);
        timeDate.setAlignment(Pos.TOP_CENTER);
        logoTime.setAlignment(Pos.TOP_CENTER);


        // sidebar
        sideMenu = new VBox();
        sideMenu.setPrefWidth(0); // set the initial width
        homeNavBtn = new Button("Home");
        homeNavBtn.setPrefWidth(200);
        transactionNavBtn = new Button("Transaction");
        transactionNavBtn.setPrefWidth(200);
        customerNavBtn = new Button("Customer");
        customerNavBtn.setPrefWidth(200);
        inventoryNavBtn = new Button("Inventory");
        inventoryNavBtn.setPrefWidth(200);

        sideMenu.setStyle("-fx-background-color: #fafafa;" +
                "-fx-padding: 10px 0px;" +
                "-fx-spacing: 10px;");

        homeNavBtn.setStyle("-fx-background-color: transparent;" +
                "-fx-text-fill: #666;" +
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;");
        homeNavBtn.setOnMouseEntered(e -> {
            homeNavBtn.setStyle("-fx-background-color: #eee;" +
                    "-fx-text-fill: #333;" +
                    "-fx-font-size: 20px;" +
                    "-fx-font-weight: bold;");
        });
        homeNavBtn.setOnMouseExited(e -> {
            homeNavBtn.setStyle("-fx-background-color: transparent;" +
                    "-fx-text-fill: #666;" +
                    "-fx-font-size: 20px;" +
                    "-fx-font-weight: bold;");
        });

        transactionNavBtn.setStyle("-fx-background-color: transparent;" +
                "-fx-text-fill: #666;" +
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;");
        transactionNavBtn.setOnMouseEntered(e -> {
            transactionNavBtn.setStyle("-fx-background-color: #eee;" +
                    "-fx-text-fill: #333;" +
                    "-fx-font-size: 20px;" +
                    "-fx-font-weight: bold;");
        });
        transactionNavBtn.setOnMouseExited(e -> {
            transactionNavBtn.setStyle("-fx-background-color: transparent;" +
                    "-fx-text-fill: #666;" +
                    "-fx-font-size: 20px;" +
                    "-fx-font-weight: bold;");
        });

        customerNavBtn.setStyle("-fx-background-color: transparent;" +
                "-fx-text-fill: #666;" +
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;");
        customerNavBtn.setOnMouseEntered(e -> {
            customerNavBtn.setStyle("-fx-background-color: #eee;" +
                    "-fx-text-fill: #333;" +
                    "-fx-font-size: 20px;" +
                    "-fx-font-weight: bold;");
        });
        customerNavBtn.setOnMouseExited(e -> {
            customerNavBtn.setStyle("-fx-background-color: transparent;" +
                    "-fx-text-fill: #666;" +
                    "-fx-font-size: 20px;" +
                    "-fx-font-weight: bold;");
        });

        inventoryNavBtn.setStyle("-fx-background-color: transparent;" +
                "-fx-text-fill: #666;" +
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;");
        inventoryNavBtn.setOnMouseEntered(e -> {
            inventoryNavBtn.setStyle("-fx-background-color: #eee;" +
                    "-fx-text-fill: #333;" +
                    "-fx-font-size: 20px;" +
                    "-fx-font-weight: bold;");
        });
        inventoryNavBtn.setOnMouseExited(e -> {
            inventoryNavBtn.setStyle("-fx-background-color: transparent;" +
                    "-fx-text-fill: #666;" +
                    "-fx-font-size: 20px;" +
                    "-fx-font-weight: bold;");
        });

        sideMenu.getChildren().addAll(homeNavBtn,transactionNavBtn,customerNavBtn, inventoryNavBtn);
        sideMenu.setStyle("-fx-background-color: #D7CDC7;");

        // Animation Side Menu
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(sideMenu.prefWidthProperty(), 0);
        KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
        timeline.getKeyFrames().add(kf);

        Timeline openTimeline = new Timeline();
        KeyValue openKV = new KeyValue(sideMenu.prefWidthProperty(), 200);
        KeyFrame openKF = new KeyFrame(Duration.millis(200), openKV);
        openTimeline.getKeyFrames().add(openKF);
        sideMenu.setOnMouseEntered(event ->{
                sideMenu.setVisible(true);
                double newWidth = 200; // the new width
                sideMenu.setPrefWidth(0); // reset the width to 0 before animating
                openTimeline.play();
        });
        sideMenu.setOnMouseExited(event-> {
            timeline.play();
        });


        // clock
        clock = new Clock();
        clock.setClock();
        VBox.setMargin(clock.getClockLabelH(), new Insets(0,0,0,0));
        //set font
        clock.getClockLabelH().setFont(new Font(130));
        clock.getClockLabelM().setFont(new Font(130));
        clock.getDate().setFont(new Font(20));
        //set spacing
        clock.getClockLabelH().setPadding(new Insets(0,0,0,0));
        clock.getClockLabelM().setPadding(new Insets(-50,0,0,0));
        //set color
        clock.getClockLabelH().setStyle("-fx-text-fill: #8C7466;");
        clock.getClockLabelM().setStyle("-fx-text-fill: #8C7466;");
        clock.getDate().setStyle("-fx-text-fill: #8C7466;");


        // title
        Label kel = new Label("GANGGUAN JAWA");
        VBox.setMargin(kel,new Insets(80, 0 ,0 ,0));
        kel.setFont(new Font(30));
        kel.setStyle("-fx-text-fill: #478660;");

        Label kel1 = new Label("Kelvin Rayhan A.");
        Label kel2 = new Label("Ditra R. Amadia");
        Label kel3 = new Label("Varraz Hazzandra A.");
        Label kel4 = new Label("Bernardus Willson");
        Label kel5 = new Label("Raditya Naufal A.");
        Label kel6 = new Label("Kenny Benaya N.");

        kel1.setFont(new Font(20));
        kel2.setFont(new Font(20));
        kel3.setFont(new Font(20));
        kel4.setFont(new Font(20));
        kel5.setFont(new Font(20));
        kel6.setFont(new Font(20));

        VBox.setMargin(kel1, new Insets(20,0,0,0));
        VBox.setMargin(kel2, new Insets(10,0,0,0));
        VBox.setMargin(kel3, new Insets(10,0,0,0));
        VBox.setMargin(kel4, new Insets(10,0,0,0));
        VBox.setMargin(kel5, new Insets(10,0,0,0));
        VBox.setMargin(kel6, new Insets(10,0,0,0));

        Label nim1 = new Label("13521005");
        Label nim2 = new Label("13521019");
        Label nim3 = new Label("13521020");
        Label nim4 = new Label("13521021");
        Label nim5 = new Label("13521022");
        Label nim6 = new Label("13521023");

// create left VBox to contain names
        VBox leftVBox = new VBox();
        leftVBox.getChildren().addAll(kel1, kel2, kel3, kel4, kel5, kel6);

// create right VBox to contain NIMs
        VBox rightVBox = new VBox();
        rightVBox.getChildren().addAll(nim1, nim2, nim3, nim4, nim5, nim6);

// create main HBox to contain both VBoxes
        HBox mainHBox = new HBox();
        mainHBox.setSpacing(20); // set spacing between the two VBoxes
        mainHBox.getChildren().addAll(leftVBox, rightVBox);

// set alignment of left VBox to left
        VBox.setMargin(leftVBox, new Insets(50, 0, 0, 0));
        leftVBox.setAlignment(Pos.TOP_LEFT);

// set alignment of right VBox to right
        VBox.setMargin(rightVBox, new Insets(50, 0, 0, 0));
        rightVBox.setAlignment(Pos.TOP_RIGHT);




        String imageUrl = "file:src/main/java/com/example/if2210_tb2_nge/pages/gangguan-jawa-logo 1.png";
        // add component to layout
        Image image = new Image(imageUrl);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(295);
        imageView.setFitWidth(210);
        logoTime.getChildren().addAll(imageView, timeDate);
        timeDate.getChildren().add(clock.getClockLabelH());
        timeDate.getChildren().add(clock.getClockLabelM());
        timeDate.getChildren().add(clock.getDate());
        container.getChildren().add(logoTime);
        container.getChildren().add(kel);
        container.getChildren().addAll(kel1,kel2,kel3,kel4,kel5,kel6);
        layout.setCenter(container);
        layout.setLeft(sideMenu);
        tab.setContent(layout);
    }

    private void toggleSideMenu() {
        // Create a new TranslateTransition with a duration of 0.5 seconds
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), sideMenu);

        if (isSideMenuOpen) {
            // Close the side menu
            translateTransition.setToX(-200); // Move the side menu to the left
        } else {
            // Open the side menu
            translateTransition.setToX(0); // Move the side menu back to its original position
        }

        translateTransition.play(); // Play the animation
        isSideMenuOpen = !isSideMenuOpen; // Toggle the side menu state
    }

}

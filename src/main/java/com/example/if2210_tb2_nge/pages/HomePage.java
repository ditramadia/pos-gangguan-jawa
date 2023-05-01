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
        transactionNavBtn = new Button("Transaction");
        customerNavBtn = new Button("Customer");
        inventoryNavBtn = new Button("Inventory");
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
        VBox.setMargin(clock.getClockLabelH(), new Insets(100,0,0,0));

        // author name and nim
        Label kel1 = new Label("Kelvin Rayhan A.       13521005");
        Label kel2 = new Label("Ditra R. Amadia         13521019");
        Label kel3 = new Label("Varraz Hazzandra A.  13521020");
        Label kel4 = new Label("Bernardus Willson      13521021");
        Label kel5 = new Label("Raditya Naufal A.       13521022");
        Label kel6 = new Label("Kenny Benaya N,       13521023");
        Label kel = new Label("Gangguan Jawa");
        VBox.setMargin(kel,new Insets(80, 0 ,0 ,0));
        VBox.setMargin(kel1,new Insets(50, 0 ,0 ,0));
        kel.setFont(new Font(40));

        String imageUrl = "file:/home/kelvin/Documents/OOP/IF2210_TB2_NGE/src/main/java/com/example/if2210_tb2_nge/pages/gangguan-jawa-logo 1.png";
        // add component to layout
        Image image = new Image(imageUrl);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(250);
        imageView.setFitWidth(250);
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

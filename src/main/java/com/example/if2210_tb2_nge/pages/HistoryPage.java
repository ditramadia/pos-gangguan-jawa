package com.example.if2210_tb2_nge.pages;

import com.example.if2210_tb2_nge.components.CustomerCard;
import com.example.if2210_tb2_nge.components.HistoryCard;
import com.example.if2210_tb2_nge.entity.Members;
import com.example.if2210_tb2_nge.repository.CustomersRepository;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.List;

public class HistoryPage extends BorderPane {
    private Label title;
    private ScrollPane scrollPane;
    private VBox cardLayout;

    public HistoryPage() {
        title = new Label("HISTORY");
        title.setFont(new Font(40));
        scrollPane = new ScrollPane();
        cardLayout = new VBox();

        for (int i = 0; i < 5; i++) {
            // create a new ItemCard
            HistoryCard historyCard = new HistoryCard();

            // add the action to the view detail button


            cardLayout.getChildren().add(historyCard.getHistoryContainer());
        }


        scrollPane.setContent(cardLayout);

        setTop(title);
        setCenter(scrollPane);
    }

    public void updateCard() throws Exception {
        cardLayout.getChildren().clear();
        // get the items and iterate over them

        for (int i = 0; i < 5; i++) {
            // create a new ItemCard
                HistoryCard historyCard = new HistoryCard();

                // add the action to the view detail button


                cardLayout.getChildren().add(historyCard.getHistoryContainer());
            }
        }

    }


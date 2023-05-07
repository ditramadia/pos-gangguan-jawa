package com.example.if2210_tb2_nge.components;

import com.example.if2210_tb2_nge.entity.Members;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import lombok.Getter;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

public class CustomerSelectionCard {
    @Getter
    private VBox cardContainer;
    private Label title;
    private VBox dataLayout;
    @Getter
    private TextField customerSelection;
    private AnimatedToggleButton toggleButton;
    private AutoCompletionBinding<String> autoCompletionBinding;
    private String[] suggestions ;

    public CustomerSelectionCard (List<Members> membersList) throws MalformedURLException {
        cardContainer = new VBox();
        title = new Label("CUSTOMER");
        suggestions = new String[membersList.size()];
        dataLayout = new VBox();
        toggleButton = new AnimatedToggleButton();


        File cssFile = new File("src/main/java/com/example/if2210_tb2_nge/style/togglebuttonstyle.css");
        String cssUrl = cssFile.toURI().toURL().toExternalForm();
        dataLayout.getStylesheets().add(cssUrl);

        // Iterate through the membersList and add each name to the suggestions array
        for (int i = 0; i < membersList.size(); i++) {
            Members member = membersList.get(i);
            suggestions[i] = member.getName();
        }

        customerSelection = new TextField();

        // Set up the auto-completion feature
        autoCompletionBinding = TextFields.bindAutoCompletion(customerSelection, suggestions);
        autoCompletionBinding.setPrefWidth(customerSelection.getPrefWidth());

        cardContainer.getChildren().addAll(title, customerSelection, dataLayout);
        customerSelection.textProperty().addListener((observable, oldValue, newValue) -> {
                dataLayout.getChildren().clear();
                // Remove the previously added labels
                // Find the selected member and add their attributes to the card container
                for (Members member : membersList) {
                    if (member.getName().equals(newValue)) {
                        Label noTelp = new Label(member.getNoTelp());
                        Label points = new Label(Integer.toString(member.getPoints()));
                        dataLayout.getChildren().addAll(noTelp,points);
                        if (member.getVip()){
                            Label vip = new Label("VIP");
                            dataLayout.getChildren().add(vip);
                            dataLayout.getChildren().add(toggleButton);
                        }
                        else {
                            Label vip = new Label();
                            dataLayout.getChildren().add(vip);
                        }

                    }
                }
        });
    }
}

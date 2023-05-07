package com.example.if2210_tb2_nge.components;

import com.example.if2210_tb2_nge.entity.Members;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

import static com.sun.javafx.event.EventUtil.fireEvent;

public class CustomerSelectionCard {
    @Getter
    private VBox cardContainer;
    private Label title;
    @Getter
    private VBox dataLayout;
    @Getter
    private TextField customerSelection;
    @Getter
    private AnimatedToggleButton toggleButton;
    private AutoCompletionBinding<String> autoCompletionBinding;
    private String[] suggestions ;
    @Getter
    @Setter
    private Members member;

    public CustomerSelectionCard (List<Members> membersList) throws MalformedURLException {
        cardContainer = new VBox();
        title = new Label("CUSTOMER");
        suggestions = new String[membersList.size()];
        dataLayout = new VBox();

        // Toggle button
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

    }
}

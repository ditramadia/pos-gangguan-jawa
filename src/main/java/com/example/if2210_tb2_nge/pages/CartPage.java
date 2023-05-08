package com.example.if2210_tb2_nge.pages;

import com.example.if2210_tb2_nge.components.CustomerSelectionCard;
import com.example.if2210_tb2_nge.components.TogglePointsEvent;
import com.example.if2210_tb2_nge.controller.CustomerController;
import com.example.if2210_tb2_nge.controller.ItemController;
import com.example.if2210_tb2_nge.controller.TransactionController;
import com.example.if2210_tb2_nge.entity.CartItem;
import com.example.if2210_tb2_nge.entity.Customers;
import com.example.if2210_tb2_nge.entity.Members;
import com.example.if2210_tb2_nge.repository.CustomersRepository;
import com.example.if2210_tb2_nge.repository.ItemsRepository;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import lombok.Getter;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sun.javafx.event.EventUtil.fireEvent;

public class CartPage {
    @Getter
    private BorderPane pageContainer;
    private ScrollPane scrollPane;
    private HBox cartLayout;
    private VBox customerPrice;
    @Getter
    private CustomerSelectionCard customerLayout;
    @Getter
    private HBox priceLayout;
    private VBox priceLabelLayout;
    private VBox priceValueLayout;
    private Label title;
    private VBox contentContainer;
    private HBox buttonLayout;
    private Double subTotal;
    private Integer usePoints;
    private Double discount;
    private Double total;
    @Getter
    private Button backBtn;
    @Getter
    private Button checkoutBtn;
    private List<Members> membersList;
    @Getter
    private Customers customer;

    public CartPage() throws MalformedURLException {
        // Values
        subTotal = 0.0;
        discount = 0.0;
        usePoints = 0;
        total = 0.0;

        // Members List
        membersList = new ArrayList<>();
        membersList = CustomersRepository.getMembersOnly();

        // Page Container
        pageContainer = new BorderPane();

        // Cart Layout
        cartLayout = new HBox();
        pageContainer.setCenter(cartLayout);

        // Scroll Pane
        scrollPane = new ScrollPane();
        cartLayout.getChildren().addAll(scrollPane);

        // Content Container
        contentContainer = new VBox();
        scrollPane.setContent(contentContainer);

        // Customer Price
        customerPrice = new VBox();
        cartLayout.getChildren().add(customerPrice);

        // Customer Layout
        customerLayout = new CustomerSelectionCard(membersList);
        customerLayout.getCustomerSelection().textProperty().addListener((observable, oldValue, newValue) -> {
            customerLayout.getDataLayout().getChildren().clear();
            // Remove the previously added labels
            // Find the selected member and add their attributes to the card container
            CustomerController.setCustomerInstance(null);
            for (Members member : membersList) {
                if (member.getName().equals(newValue)) {
                    CustomerController.setCustomerInstance(member.getId());
                    customer = CustomerController.getCustomerInstance();
                    Label noTelp = new Label(member.getNoTelp());
                    Label points = new Label(Integer.toString(member.getPoints()) + " pts");
                    customerLayout.setMember(member);
                    customerLayout.getDataLayout().getChildren().addAll(noTelp,points);

                    priceLabelLayout.getChildren().clear();
                    priceValueLayout.getChildren().clear();

                    // add subtotal label
                    Label subTotalLabel = new Label("Subtotal");
                    priceLabelLayout.getChildren().add(subTotalLabel);

                    subTotal = TransactionController.getBillInstance().getSubtotal();
                    Label subTotalValueLabel = new Label(Double.toString(subTotal));
                    priceValueLayout.getChildren().add(subTotalValueLabel);

                    // add vip discount
                    if (member.getVip() && member.getActive()){
                        Label vip = new Label("VIP");
                        customerLayout.getDataLayout().getChildren().add(vip);

                        Label discountLabel = new Label("Discount");
                        priceLabelLayout.getChildren().add(discountLabel);

                        discount = subTotal * 0.1;
                        Label discountValueLabel = new Label("-" + Double.toString(discount));
                        priceValueLayout.getChildren().add(discountValueLabel);
                    }

                    // add use points toggle
                    if (member.getPoints() > 0 && member.getActive()) {
                        customerLayout.getDataLayout().getChildren().add(customerLayout.getToggleButton());
                    }

                    // add total label
                    total = subTotal - usePoints - discount;
                    Label totalLabel = new Label("TOTAL");
                    totalLabel.setFont(new Font(20));
                    priceLabelLayout.getChildren().add(totalLabel);

                    Label totalValueLabel = new Label(Double.toString(total));
                    totalValueLabel.setFont(new Font(20));
                    priceValueLayout.getChildren().add(totalValueLabel);

                    customerLayout.getToggleButton().addEventHandler(TogglePointsEvent.TOGGLE_BUTTON_CLICKED, new EventHandler<TogglePointsEvent>() {
                        @Override
                        public void handle(TogglePointsEvent event) {
                            priceLabelLayout.getChildren().clear();
                            priceValueLayout.getChildren().clear();

                            // add sub total
                            Label subTotalLabel = new Label("Subtotal");
                            priceLabelLayout.getChildren().add(subTotalLabel);

                            subTotal = TransactionController.getBillInstance().getSubtotal();
                            Label subTotalValueLabel = new Label(Double.toString(subTotal));
                            priceValueLayout.getChildren().add(subTotalValueLabel);

                            // add vip discount
                            if (member.getVip() && member.getActive()){
                                Label discountLabel = new Label("Discount");
                                priceLabelLayout.getChildren().add(discountLabel);

                                discount = subTotal * 0.1;
                                Label discountValueLabel = new Label("-" + Double.toString(discount));
                                priceValueLayout.getChildren().add(discountValueLabel);
                            }

                            // add use points label
                            if (member.getPoints() > 0 && !customerLayout.getToggleButton().getActive()) {
                                usePoints = member.getPoints();
                                Label usePointsLabel = new Label("Points");
                                priceLabelLayout.getChildren().add(usePointsLabel);

                                usePoints = customerLayout.getMember().getPoints();
                                Label usePointsValueLabel = new Label("-" + Integer.toString(usePoints));
                                priceValueLayout.getChildren().add(usePointsValueLabel);
                            } else {
                                usePoints = 0;
                            }

                            // add total label
                            total = subTotal - usePoints - discount;
                            Label totalLabel = new Label("TOTAL");
                            totalLabel.setFont(new Font(20));
                            priceLabelLayout.getChildren().add(totalLabel);

                            Label totalValueLabel = new Label(Double.toString(total));
                            totalValueLabel.setFont(new Font(20));
                            priceValueLayout.getChildren().add(totalValueLabel);
                        }
                    });
                }
            }
        });
        customerPrice.getChildren().add(customerLayout.getCardContainer());

        // Price Layout
        priceLayout = new HBox();
        customerPrice.getChildren().add(priceLayout);

        // Price Label Layout
        priceLabelLayout = new VBox();
        priceLayout.getChildren().add(priceLabelLayout);

        // Price Value Layout
        priceValueLayout = new VBox();
        priceLayout.getChildren().add(priceValueLayout);

        // Button layout
        buttonLayout = new HBox();
        customerPrice.getChildren().add(buttonLayout);

        // Title
        title = new Label("CART");
        title.setFont(new Font(30));
        pageContainer.setTop(title);

        // Back button
        backBtn = new Button("Back");
        buttonLayout.getChildren().add(backBtn);

        // Checkout button
        checkoutBtn = new Button("Checkout");
        buttonLayout.getChildren().add(checkoutBtn);
    }

    public void setCart (){
        List<CartItem> cartItems = TransactionController.getBillInstance().getCart();
        contentContainer = new VBox();
        for (CartItem item : cartItems){
            if (item.getQuantity() != 0) {
                ItemController.setItemInstance(item.getItem().getId());
                HBox newitemContainer = new HBox();
                newitemContainer.setStyle("-fx-background-color: #D7CDC7; -fx-background-radius: 20");
                newitemContainer.setPrefWidth(800);
                newitemContainer.setPrefHeight(50);
                Label newitemName = new Label(item.getItem().getName());
                Label newitemPrice = new Label(Integer.toString(item.getItem().getPrice()));
                Label newquantity = new Label(Integer.toString(item.getQuantity()));

                newitemContainer.getChildren().addAll(newitemName, newquantity, newitemPrice);
                VBox.setMargin(newitemContainer, new Insets(30));
                contentContainer.getChildren().add(newitemContainer);
            }
        }

        // add subtotal label
        Label subTotalLabel = new Label("Subtotal");
        priceLabelLayout.getChildren().add(subTotalLabel);

        subTotal = TransactionController.getBillInstance().getSubtotal();
        Label subTotalValueLabel = new Label(Double.toString(subTotal));
        priceValueLayout.getChildren().add(subTotalValueLabel);

        // add total label
        total = subTotal - usePoints - discount;
        Label totalLabel = new Label("TOTAL");
        totalLabel.setFont(new Font(20));
        priceLabelLayout.getChildren().add(totalLabel);

        Label totalValueLabel = new Label(Double.toString(total));
        totalValueLabel.setFont(new Font(20));
        priceValueLayout.getChildren().add(totalValueLabel);

        scrollPane.setContent(contentContainer);
    }

    public void resetPage() {
        contentContainer.getChildren().clear();
        customerLayout.getCustomerSelection().setText("");
        customerLayout.getDataLayout().getChildren().clear();
        priceValueLayout.getChildren().clear();
        priceLabelLayout.getChildren().clear();
    }
}

package com.example.if2210_tb2_nge.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Bill {
    @Getter
    @Setter
    protected List<CartItem> cart;
    @Getter
    @Setter
    protected Double subtotal;

    public Bill() {
        this.cart = new ArrayList<CartItem>();
        this.subtotal = 0.0;
    }

    public Bill(List<CartItem> cart) {
        this.cart = cart;
        this.subtotal = 0.0;
        for (CartItem cartItem: cart) {
            subtotal += cartItem.getItem().getPrice() * cartItem.getQuantity();
        }
    }

    public Bill(List<CartItem> cart, Double subtotal) {
        this.cart = cart;
        this.subtotal = subtotal;
    }

    public Customers getCustomer() {
        return null;
    }

    public Integer getPointsUsed() {
        return -1;
    }

    public Double getTotal() {
        return -1.0;
    }
}

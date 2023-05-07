package com.example.if2210_tb2_nge.entity;

import lombok.Getter;

public class CartItem {
    @Getter
    private int id;
    @Getter
    private int quantity;

    public CartItem(int id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }


}

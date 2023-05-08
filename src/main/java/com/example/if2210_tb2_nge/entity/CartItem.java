package com.example.if2210_tb2_nge.entity;

import lombok.Getter;

public class CartItem {
    @Getter
    private Items item;
    @Getter
    private int quantity;

    public CartItem(Items item, int quantity) {
        this.item = new Items(item);
        this.quantity = quantity;
    }


}

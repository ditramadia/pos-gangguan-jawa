package com.example.if2210_tb2_nge.entity;

import lombok.Getter;
import lombok.Setter;

public class Items {
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private Integer price;
    @Getter
    @Setter
    private Integer buyPrice;
    @Getter
    @Setter
    private Integer stock;
    @Getter
    @Setter
    private String category;
    @Getter
    @Setter
    private String image;

    public Items(Integer id, String name, Integer price, Integer buyPrice, Integer stock, String category, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.buyPrice = buyPrice;
        this.stock = stock;
        this.category = category;
        this.image = image;
    }
    public Items(Items item) {
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.buyPrice = item.getBuyPrice();
        this.stock = item.getStock();
        this.category = item.getCategory();
        this.image = item.getImage();
    }
}

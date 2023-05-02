package com.example.if2210_tb2_nge.adapter;

public class Item {
    private int id;
    private String name;
    private String image;
    private String category;
    private int stock;
    private int price;
    private int buyPrice;

    public Item(int id, String name, String image, String category, int stock, int price, int buyPrice) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.category = category;
        this.stock = stock;
        this.price = price;
        this.buyPrice = buyPrice;
    }

    // Getter and setter methods for all fields

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", category='" + category + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                ", buyPrice=" + buyPrice +
                '}';
    }
}


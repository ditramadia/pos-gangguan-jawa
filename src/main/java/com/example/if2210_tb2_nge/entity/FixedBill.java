package com.example.if2210_tb2_nge.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class FixedBill extends Bill{
    @Getter
    @Setter
    private Customers customer;
    @Getter
    @Setter
    private Integer pointsUsed;
    @Getter
    @Setter
    private Double discount;
    @Getter
    @Setter
    private Double total;

    public FixedBill() {
        super();
        this.customer = null;
        this.pointsUsed = 0;
        this.discount = 0.0;
        this.total = 0.0;
    }

    public FixedBill(Bill temporaryBill, Customers customer, Integer pointsUsed) {
        super(temporaryBill.getCart());
        FixedBill retFixedBill = new FixedBill();
        retFixedBill.setCart(temporaryBill.getCart());
        this.customer = customer;
        this.pointsUsed = pointsUsed;
        this.discount = 0.0;
        if (customer instanceof Members && customer.getVip()) {
            this.discount = this.subtotal * 0.1;
        }
        this.total = this.subtotal - this.discount - this.pointsUsed;
    }

    public FixedBill(List<CartItem> cart, Double subtotal, Double discount, Integer PointsUsed, Double total) {
        super(cart, subtotal);
        this.discount = discount;
        this.pointsUsed = PointsUsed;
        this.total = total;
    }

}

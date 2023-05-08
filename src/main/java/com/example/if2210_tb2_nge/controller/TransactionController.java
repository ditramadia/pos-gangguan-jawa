package com.example.if2210_tb2_nge.controller;

import com.example.if2210_tb2_nge.entity.Bill;
import com.example.if2210_tb2_nge.entity.CartItem;
import com.example.if2210_tb2_nge.entity.Items;
import com.example.if2210_tb2_nge.repository.CustomersRepository;
import com.example.if2210_tb2_nge.repository.ItemsRepository;
import lombok.Getter;
import lombok.Setter;

public class TransactionController {
    @Getter
    @Setter
    private static Bill billInstance;

    public static void setbillInstance(Bill billInstance, Boolean check){
        setBillInstance(billInstance);

        if (billInstance.getCustomer() == null) {
            CustomerController.setCustomerInstance(null);
        } else {
            CustomerController.setCustomerInstance(billInstance.getCustomer().getId());
        }

        // Update stock
        for (CartItem cartItem : billInstance.getCart()) {
            ItemsRepository.updateItems(cartItem.getItem(), cartItem.getQuantity());
            System.out.println(cartItem.getItem().getId());
        }

        // Update customer points
        if (billInstance.getPointsUsed() > 0) {
            CustomerController.getCustomerInstance().setPoints(0);
        }
        if (CustomerController.getCustomerInstance().getVip()) {
            Double addedPoints = CustomerController.getCustomerInstance().getPoints() + (billInstance.getTotal() * 0.01);
            CustomerController.getCustomerInstance().setPoints(CustomerController.getCustomerInstance().getPoints() + addedPoints.intValue());
        }
        CustomersRepository.updateCustomer(CustomerController.getCustomerInstance());

        // Update bills
    }
}


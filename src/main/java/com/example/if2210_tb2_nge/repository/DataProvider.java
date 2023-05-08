package com.example.if2210_tb2_nge.repository;

import com.example.if2210_tb2_nge.entity.Customers;
import com.example.if2210_tb2_nge.entity.Items;
import lombok.Getter;

import java.util.List;

public class DataProvider {
    @Getter
    private List<Customers> customersList;

    @Getter
    private List<Items> itemsList;

    public DataProvider() {
        customersList = CustomersRepository.getCustomers();
        itemsList = ItemsRepository.getItems();
    }
}

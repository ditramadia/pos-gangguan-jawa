package com.example.if2210_tb2_nge.repository;

import com.example.if2210_tb2_nge.entity.Customers;
import com.example.if2210_tb2_nge.entity.Items;

import java.util.List;

public class DataProvider {
    public static List<Customers> getCustomersList() {
        return CustomersRepository.getCustomers();
    }
    public static List<Items> getItemList() {
        return ItemsRepository.getItems();
    }
//    public static List<Bill> getBillList() {
//        return BillsRepository.getBills();
//    }
}

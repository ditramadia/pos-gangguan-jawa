package com.example.if2210_tb2_nge.controller;

import com.example.if2210_tb2_nge.entity.Customers;
import com.example.if2210_tb2_nge.entity.Members;
import com.example.if2210_tb2_nge.repository.CustomersRepository;
import lombok.Getter;

import java.util.List;

public class CustomerController {

    @Getter
    private static Customers customerInstance;

    public static void setCustomerInstance(Integer id) {
        List<Customers> customers = CustomersRepository.getCustomers();
        for (Customers customer : customers) {
            if (customer.getId().equals(id)) {
                customerInstance = customer;
                break;
            }
        }
    }
}

package com.example.if2210_tb2_nge.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

public class CustomersRepository {
    private static List<Map<String, Object>> customers;

    public static void setCustomersRepository(Object obj) throws JsonProcessingException {
        String json = null;
        json = new ObjectMapper().writeValueAsString(obj);
        Gson gson = new Gson();
        Map<String, List<Map<String, Object>>> data = gson.fromJson(json, Map.class);
        List<Map<String, Object>> customersList = data.get("customers");
        if (customersList == null) {
        }
        else {
            customers = customersList;
        }
    }

    public static Object saveCustomers() {
        return Map.of("customers", customers);
    }

    public static List<Map<String, Object>> getCustomers() {
        return customers;
    }

    public static void printCustomers() {
        for (Map<String, Object> customer : customers) {
            System.out.println(customer.get("name"));
        }
    }
}


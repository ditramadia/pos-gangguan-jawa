package com.example.if2210_tb2_nge.repository;

import com.example.if2210_tb2_nge.entity.Customers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomersRepository {
    @Getter
    private static List<Customers> customers;

    public static void setCustomersRepository(Object obj) throws JsonProcessingException {
        String json = null;
        json = new ObjectMapper().writeValueAsString(obj);
        Gson gson = new Gson();
        Map<String, List<Map<String, Object>>> data = gson.fromJson(json, Map.class);
        List<Map<String, Object>> customersList = data.get("customers");
        if (customersList == null) {
        }
        else {
            customers = new ArrayList<>();
            for (Map<String, Object> customer : customersList) {
                Double id;
                try {
                    id = (Double) customer.get("id");
                } catch (Exception e) {
                    id = Double.parseDouble((String) customer.get("id"));
                }
                String name = (String) customer.get("name");
                String noTelp = (String) customer.get("noTelp");
                Boolean vip = (Boolean) customer.get("vip");
                Boolean active = (Boolean) customer.get("active");
                Customers newCustomer = new Customers(id.intValue(), name, noTelp, vip, active);
                customers.add(newCustomer);
            }
        }
    }

    public static Object saveCustomers() {
        return Map.of("customers", customers);
    }
}
package com.example.if2210_tb2_nge.repository;

import com.example.if2210_tb2_nge.entity.Customers;
import com.example.if2210_tb2_nge.entity.Items;
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
                Double points;
                try {
                    points = (Double) customer.get("points");
                } catch (Exception e) {
                    points = Double.parseDouble((String) customer.get("points"));
                }
                Boolean vip;
                Boolean active;
                try {
                    vip = Boolean.parseBoolean((String) customer.get("vip"));
                    active = Boolean.parseBoolean((String) customer.get("active"));
                }
                catch (Exception e) {
                    vip = (Boolean) customer.get("vip");
                    active = (Boolean) customer.get("active");
                }
                Customers newCustomer = new Customers(id.intValue(), name, noTelp, points.intValue(), vip, active);
                customers.add(newCustomer);
            }
        }
    }

    public static Object saveCustomers() {
        // convert List of Items to hashmap
        List<Map<String, Object>> customersList = new ArrayList<>();
        for (Customers customer : customers) {
            Map<String, Object> customerMap = Map.of(
                    "id", customer.getId(),
                    "name", customer.getName(),
                    "noTelp", customer.getNoTelp(),
                    "points", customer.getPoints(),
                    "vip", customer.getVip(),
                    "active", customer.getActive()
            );
            customersList.add(customerMap);
        }
        Map<String, List<Map<String, Object>>> data = Map.of("customers", customersList);
        return data;
    }

    public static void updateCustomer(Customers customers) {
        for (Customers customer : CustomersRepository.customers) {
            if (customer.getId() == customers.getId()) {
                customer.setName(customers.getName());
                customer.setNoTelp(customers.getNoTelp());
                customer.setPoints(customers.getPoints());
                customer.setVip(customers.getVip());
                customer.setActive(customers.getActive());
                break;
            }
        }
    }
}
package com.example.if2210_tb2_nge.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

public class Customers {
    private List<Map<String, Object>> customers;

    public Customers(Object obj) {
        String json = null;
        try {
            json = new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }

        Gson gson = new Gson();
        Map<String, List<Map<String, Object>>> data = gson.fromJson(json, Map.class);
        try {
            List<Map<String, Object>> customers = data.get("customers");
            this.customers = customers;
        } catch (Exception exc) {

        }
    }

    public void printCustomers() {
        try {
            for (Map<String, Object> customer : customers) {
                System.out.println(customer.get("name"));
            }
        }
        catch (Exception exc) {

        }
    }
}

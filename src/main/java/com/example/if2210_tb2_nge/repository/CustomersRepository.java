package com.example.if2210_tb2_nge.repository;

import com.example.if2210_tb2_nge.controller.CustomerController;
import com.example.if2210_tb2_nge.entity.Items;
import com.example.if2210_tb2_nge.entity.Members;
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

    public static List<Members> getMembersOnly() {
        List<Members> members = new ArrayList<>();
        for (Customers customer : customers) {
            if (customer instanceof Members) {
                members.add((Members) customer);
            }
        }
        return members;
    }

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
                if (!name.equals("")) {
                    Members newMember = new Members(id.intValue(), name, noTelp, points.intValue(), vip, active);
                    customers.add(newMember);
                }
                else {
                    Customers newCustomers = new Customers(id.intValue());
                    customers.add(newCustomers);
                }
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
                    "vip", customer.getVip(),
                    "active", customer.getActive()
            );
            customersList.add(customerMap);
        }
        Map<String, List<Map<String, Object>>> data = Map.of("customers", customersList);
        return data;
    }

    public static void addCustomer(Customers customer) throws Exception {
        List<Customers> customers = CustomersRepository.getCustomers();
        Integer id = getLastIdCustomers() + 1;
        Customers newCustomer = new Members(id, customer.getName(), customer.getNoTelp(), customer.getPoints(), customer.getVip(), customer.getActive());
        customers.add(newCustomer);
    }

    public static void updateCustomer(Customers customers) {
        for (Customers customer : CustomersRepository.customers) {
            if (customer.getId() == customers.getId() && customers.getName() != "") {
                customer.setName(customers.getName());
                customer.setNoTelp(customers.getNoTelp());
                customer.setPoints(customers.getPoints());
                customer.setVip(customers.getVip());
                customer.setActive(customers.getActive());
                break;
            }
        }
    }

    public static Integer getLastIdCustomers() {
        List<Customers> customers = CustomersRepository.getCustomers();
        Integer lastId = 0;
        for (Customers customer : customers) {
            if (customer.getId() > lastId) {
                lastId = customer.getId();
            }
        }
        return lastId;
    }
}
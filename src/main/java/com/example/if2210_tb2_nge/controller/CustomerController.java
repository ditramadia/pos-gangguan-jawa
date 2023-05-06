package com.example.if2210_tb2_nge.controller;

import com.example.if2210_tb2_nge.repository.CustomersRepository;

import java.util.List;
import java.util.Map;

public class CustomerController {

    public static void addCustomer(String name, String noTelp, Boolean active,
                                Boolean vip) throws Exception {
        List<Map<String, Object>> customers = CustomersRepository.getCustomers();
        Integer id = CustomerController.getLastIdCustomer();
        customers.add(Map.of("id", id, "name", name, "notelp", noTelp, "active", active, "vip", vip));
        CustomersRepository.setCustomersRepository(Map.of("customers", customers));
    }

    public static Map<String, Object> getCustomer(Integer id) throws Exception {
        List<Map<String, Object>> customers = CustomersRepository.getCustomers();
        for (Map<String, Object> customer : customers) {
            Double Id;
            try {
                Id = (Double) customer.get("id");
            } catch (Exception e) {
                Id = Double.parseDouble((String) customer.get("id"));
            }
            if (Id.intValue() == id) {
                return customer;
            }
        }
        return null;
    }

    public static void deleteCustomers(Integer id) throws Exception {
        List<Map<String, Object>> customers = CustomersRepository.getCustomers();
        for (Map<String, Object> customer : customers) {
            Double Id;
            try {
                Id = (Double) customer.get("id");
            } catch (Exception e) {
                Id = Double.parseDouble((String) customer.get("id"));
            }
            if (Id.intValue() == id) {
                customers.remove(customer);
                break;
            }
        }
        CustomersRepository.setCustomersRepository(Map.of("customers", customers));
    }

    public static void updateCustomers(Integer id,String name, String noTelp, Boolean active,
                                   Boolean vip) throws Exception {
        List<Map<String, Object>> customers = CustomersRepository.getCustomers();
        for (Map<String, Object> customer : customers) {
            Double Id;
            try {
                Id = (Double) customer.get("id");
            } catch (Exception e) {
                Id = Double.parseDouble((String) customer.get("id"));
            }
            if (Id.intValue() == id) {
                customer.put("name", name);
                customer.put("noTelp", noTelp);
                customer.put("active", active);
                customer.put("vip", vip);
                break;
            }
        }
        CustomersRepository.setCustomersRepository(Map.of("customers", customers));
    }

    public static Integer getLastIdCustomer() throws Exception {
        List<Map<String, Object>> customers = CustomersRepository.getCustomers();
        try {
            Integer lastId = 0;
            for (Map<String, Object> customer : customers) {
                if (Integer.parseInt(customer.get("id").toString()) > lastId) {
                    lastId = Integer.parseInt(customer.get("id").toString());
                }
            }
            return lastId;
        } catch (Exception e) {
            return 0;
        }
    }
}

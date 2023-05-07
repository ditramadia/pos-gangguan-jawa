package com.example.if2210_tb2_nge.repository;

import com.example.if2210_tb2_nge.entity.Bill;
import com.example.if2210_tb2_nge.entity.Customers;
import com.example.if2210_tb2_nge.entity.Members;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BillsRepository {
    private static List<Bill> bills;

    public static void setBillsRepository(Object obj) throws JsonProcessingException {
        String json = null;
        json = new ObjectMapper().writeValueAsString(obj);
        Gson gson = new Gson();
        Map<String, List<Map<String, Object>>> data = gson.fromJson(json, Map.class);
        List<Map<String, Object>> billList = data.get("bills");
        if (billList == null) {
        }
        else {
            bills = new ArrayList<>();
            for (Map<String, Object> bill : billList) {
                
            }
        }
    }
}

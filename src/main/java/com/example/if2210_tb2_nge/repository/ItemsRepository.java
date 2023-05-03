package com.example.if2210_tb2_nge.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

public class ItemsRepository {
    private static List<Map<String, Object>> items;

    public static void setItemsRepository(Object obj) throws JsonProcessingException {
        String json = null;
        json = new ObjectMapper().writeValueAsString(obj);
        Gson gson = new Gson();
        Map<String, List<Map<String, Object>>> data = gson.fromJson(json, Map.class);
        List<Map<String, Object>> itemsList = data.get("items");
        items = itemsList;
    }

    public static Object saveItems() {
        return Map.of("items", items);
    }

    public static List<Map<String, Object>> getItems() {
        return items;
    }

    public static void printItems() {
        for (Map<String, Object> item : items) {
            System.out.println(item.get("name"));
        }
    }
}

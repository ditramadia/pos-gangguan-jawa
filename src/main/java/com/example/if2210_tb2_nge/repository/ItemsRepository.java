package com.example.if2210_tb2_nge.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

public class ItemsRepository {
    private static List<Map<String, Object>> items;

    public static void setItemsRepository(Object obj) {
        String json = null;
        try {
            json = new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }

        Gson gson = new Gson();
        Map<String, List<Map<String, Object>>> data = gson.fromJson(json, Map.class);
        try {
            List<Map<String, Object>> itemsList = data.get("items");
            items = itemsList;
        } catch (Exception exc) {

        }
    }

    public static Object saveItems() {
        return Map.of("items", items);
    }

    public static List<Map<String, Object>> getItems() {
        return items;
    }

    public static void printItems() {
        try {
            for (Map<String, Object> item : items) {
                System.out.println(item.get("name"));
            }
        }
        catch (Exception exc) {

        }
    }
}

package com.example.if2210_tb2_nge.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

public class Items {
    private List<Map<String, Object>> items;

    public Items(Object obj) {
        String json = null;
        try {
            json = new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }

        Gson gson = new Gson();
        Map<String, List<Map<String, Object>>> data = gson.fromJson(json, Map.class);
        try {
            List<Map<String, Object>> items = data.get("items");
            this.items = items;
        } catch (Exception exc) {

        }
    }

    public Object getItems() {
        return Map.of("items", items);
    }

    public void printItems() {
        try {
            for (Map<String, Object> item : items) {
                System.out.println(item.get("name"));
            }
        }
        catch (Exception exc) {

        }
    }
}

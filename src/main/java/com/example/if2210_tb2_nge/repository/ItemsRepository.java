package com.example.if2210_tb2_nge.repository;

import com.example.if2210_tb2_nge.entity.Items;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemsRepository {
    @Getter
    private static List<Items> items = new ArrayList<>();

    public static void setItemsRepository(Object obj) throws JsonProcessingException {
        String json = null;
        json = new ObjectMapper().writeValueAsString(obj);
        Gson gson = new Gson();
        Map<String, List<Map<String, Object>>> data = gson.fromJson(json, Map.class);
        List<Map<String, Object>> itemsList = data.get("items");
        if (itemsList == null) {
        }
        else {
            for (Map<String, Object> item : itemsList) {
                Double id;
                try {
                    id = (Double) item.get("id");
                } catch (Exception e) {
                    id = Double.parseDouble((String) item.get("id"));
                }
                String name = (String) item.get("name");
                Double price;
                try {
                    price = (Double) item.get("price");
                } catch (Exception e) {
                    price = Double.parseDouble((String) item.get("price"));
                }
                Double buyPrice;
                try {
                    buyPrice = (Double) item.get("buyPrice");
                } catch (Exception e) {
                    buyPrice = Double.parseDouble((String) item.get("buyPrice"));
                }
                Double stock;
                try {
                    stock = (Double) item.get("stock");
                } catch (Exception e) {
                    stock = Double.parseDouble((String) item.get("stock"));
                }
                String category = (String) item.get("category");
                String image = (String) item.get("image");
                Items newItem = new Items(id.intValue(), name, price.intValue(), buyPrice.intValue(), stock.intValue(), category, image);
                items.add(newItem);
            }
        }
    }

    public static Object saveItems() {
        return Map.of("items", items);
    }
}

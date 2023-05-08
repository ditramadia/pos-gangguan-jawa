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
    private static List<Items> items;

    public static void setItemsRepository(Object obj) throws JsonProcessingException {
        String json = null;
        json = new ObjectMapper().writeValueAsString(obj);
        Gson gson = new Gson();
        Map<String, List<Map<String, Object>>> data = gson.fromJson(json, Map.class);
        List<Map<String, Object>> itemsList = data.get("items");
        if (itemsList == null) {
        }
        else {
            items = new ArrayList<>();
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

    public static void addItems(String name, Integer price, Integer buyPrice,
                                Integer stock, String category, String image) throws Exception {
        List<Items> items = ItemsRepository.getItems();
        Integer id = getLastIdItems() + 1;
        Items newItem = new Items(id, name, price, buyPrice, stock, category, image);
        items.add(newItem);
    }

    public static void deleteItems(Integer id) throws Exception {
        List<Items> items = ItemsRepository.getItems();
        for (Items item : items) {
            if (item.getId() == id) {
                items.remove(item);
                break;
            }
        }
    }

    public static void updateItems(Integer id, String name, Integer price, Integer buyPrice,
                                   Integer stock, String category) throws Exception {
        List<Items> items = ItemsRepository.getItems();
        for (Items item : items) {
            if (item.getId() == id) {
                item.setName(name);
                item.setPrice(price);
                item.setBuyPrice(buyPrice);
                item.setStock(stock);
                item.setCategory(category);
                break;
            }
        }
    }

    public static void updateItems(Items items) {
        for (Items item : ItemsRepository.items) {
            if (item.getId() == items.getId()) {
                item.setName(items.getName());
                item.setPrice(items.getPrice());
                item.setBuyPrice(items.getBuyPrice());
                item.setStock(items.getStock());
                item.setCategory(items.getCategory());
                break;
            }
        }
    }

    public static void updateItems(Items items, Integer quantitySold) {
        for (Items item : ItemsRepository.items) {
            if (item.getId() == items.getId()) {
                item.setName(items.getName());
                item.setPrice(items.getPrice());
                item.setBuyPrice(items.getBuyPrice());
                item.setStock(items.getStock() - quantitySold);
                item.setCategory(items.getCategory());
                break;
            }
        }
    }


    public static Object saveItems() {
        // convert List of Items to hashmap
        List<Map<String, Object>> itemsList = new ArrayList<>();
        for (Items item : items) {
            Map<String, Object> itemMap = Map.of(
                    "id", item.getId(),
                    "name", item.getName(),
                    "price", item.getPrice(),
                    "buyPrice", item.getBuyPrice(),
                    "stock", item.getStock(),
                    "category", item.getCategory(),
                    "image", item.getImage()
            );
            itemsList.add(itemMap);
        }
        Map<String, List<Map<String, Object>>> data = Map.of("items", itemsList);
        return data;
    }

    public static Integer getLastIdItems() throws Exception {
        List<Items> items = ItemsRepository.getItems();
        Integer lastId = 0;
        for (Items item : items) {
            if (item.getId() > lastId) {
                lastId = item.getId();
            }
        }
        return lastId;
    }
    public static Items getItemsById(Integer id) throws Exception {
        List<Items> items = ItemsRepository.getItems();
        for (Items item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}

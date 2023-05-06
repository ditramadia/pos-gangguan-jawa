package com.example.if2210_tb2_nge.controller;

import com.example.if2210_tb2_nge.repository.ItemsRepository;

import java.util.List;
import java.util.Map;

public class ItemController {

    public static void addItems(String name, Integer price, Integer buyPrice,
                                Integer stock, String category, String image) throws Exception {
        List<Map<String, Object>> items = ItemsRepository.getItems();
        Integer id = ItemController.getLastIdItems();
        Object[] item = {id, name, price, buyPrice, stock, category, image};
        items.add(Map.of("id", id, "name", name, "price", price, "buyPrice", buyPrice, "stock", stock, "category", category, "image", image));
        ItemsRepository.setItemsRepository(Map.of("items", items));
    }

    public static Map<String, Object> getItem(Integer id) throws Exception {
        List<Map<String, Object>> items = ItemsRepository.getItems();
        for (Map<String, Object> item : items) {
            Double Id;
            try {
                Id = (Double) item.get("id");
            } catch (Exception e) {
                Id = Double.parseDouble((String) item.get("id"));
            }
            if (Id.intValue() == id) {
                return item;
            }
        }
        return null;
    }

    public static void deleteItems(Integer id) throws Exception {
        List<Map<String, Object>> items = ItemsRepository.getItems();
        for (Map<String, Object> item : items) {
            Double Id;
            try {
                Id = (Double) item.get("id");
            } catch (Exception e) {
                Id = Double.parseDouble((String) item.get("id"));
            }
            if (Id.intValue() == id) {
                items.remove(item);
                break;
            }
        }
        ItemsRepository.setItemsRepository(Map.of("items", items));
    }

    public static void updateItems(Integer id, String name, Integer price, Integer buyPrice,
                                   Integer stock, String category) throws Exception {
        List<Map<String, Object>> items = ItemsRepository.getItems();
        for (Map<String, Object> item : items) {
            Double Id;
            try {
                Id = (Double) item.get("id");
            } catch (Exception e) {
                Id = Double.parseDouble((String) item.get("id"));
            }
            if (Id.intValue() == id) {
                item.put("name", name);
                item.put("price", price);
                item.put("buyPrice", buyPrice);
                item.put("stock", stock);
                item.put("category", category);
//                item.put("image", image);
                break;
            }
        }
        ItemsRepository.setItemsRepository(Map.of("items", items));
    }

    public static Integer getLastIdItems() throws Exception {
        List<Map<String, Object>> items = ItemsRepository.getItems();
        try {
            Integer lastId = 0;
            for (Map<String, Object> item : items) {
                if (Integer.parseInt(item.get("id").toString()) > lastId) {
                    lastId = Integer.parseInt(item.get("id").toString());
                }
            }
            return lastId;
        } catch (Exception e) {
            return 0;
        }
    }
}

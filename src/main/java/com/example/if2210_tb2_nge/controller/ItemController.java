package com.example.if2210_tb2_nge.controller;

import com.example.if2210_tb2_nge.entity.Items;
import com.example.if2210_tb2_nge.repository.ItemsRepository;

import java.util.List;
import java.util.Map;

public class ItemController {

    public static void addItems(String name, Integer price, Integer buyPrice,
                                Integer stock, String category, String image) throws Exception {
        List<Items> items = ItemsRepository.getItems();
        Integer id = getLastIdItems() + 1;
        Items newItem = new Items(id, name, price, buyPrice, stock, category, image);
        items.add(newItem);
        ItemsRepository.setItemsRepository(Map.of("items", items));
    }

    public static List<Items> getItem(Integer id) throws Exception {
        List<Items> items = ItemsRepository.getItems();
        for (Items item : items) {
            if (item.getId() == id) {
                return List.of(item);
            }
        }
        return null;
    }

    public static void deleteItems(Integer id) throws Exception {
        List<Items> items = ItemsRepository.getItems();
        for (Items item : items) {
            if (item.getId() == id) {
                items.remove(item);
                break;
            }
        }
        ItemsRepository.setItemsRepository(Map.of("items", items));
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
}

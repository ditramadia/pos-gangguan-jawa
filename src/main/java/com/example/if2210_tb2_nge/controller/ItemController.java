package com.example.if2210_tb2_nge.controller;

import com.example.if2210_tb2_nge.entity.Items;
import com.example.if2210_tb2_nge.repository.ItemsRepository;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

public class ItemController {
    @Getter
    private static Items itemInstance;

    public static void setItemInstance(Integer id) {
        List<Items> items = ItemsRepository.getItems();
        for (Items item : items) {
            if (item.getId().equals(id)) {
                itemInstance = item;
                break;
            }
        }
    }
}

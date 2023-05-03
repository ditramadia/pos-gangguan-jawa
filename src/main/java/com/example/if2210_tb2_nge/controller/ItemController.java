package com.example.if2210_tb2_nge.controller;

import com.example.if2210_tb2_nge.repository.ItemsRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
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

//    public static void updateItemsJSON(int idItem, String name, int price, int buyPrice,
//                                                int stock, String category, String image) throws  Exception {
//        JSONObject jsonObj = readItemsJSON("src/main/java/com/example/if2210_tb2_nge/database/Items.json");
//        JSONArray itemsArray = (JSONArray) jsonObj.get("items");
//
//        for (Object o : itemsArray) {
//            JSONObject item = (JSONObject) o;
//            if (Integer.parseInt(item.get("id").toString()) == idItem) {
//                item.put("name", name);
//                item.put("price", price);
//                item.put("buyPrice", buyPrice);
//                item.put("stock", stock);
//                item.put("category", category);
//                item.put("image", image);
//                break;
//            }
//        }
//
//        byte[] jsonBytes = jsonObj.toJSONString().getBytes();
//        Files.write(Paths.get("src/main/java/com/example/if2210_tb2_nge/database/Items.json"), jsonBytes, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.SYNC);
//    }

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

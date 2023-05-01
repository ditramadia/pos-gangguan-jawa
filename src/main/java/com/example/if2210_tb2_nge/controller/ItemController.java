package com.example.if2210_tb2_nge.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class ItemController {
    public static int getLastIdItems() throws Exception {
        JSONObject jsonObj = readItemsJSON("src/main/java/com/example/if2210_tb2_nge/database/Items.json");
        JSONArray itemsArray = (JSONArray) jsonObj.get("items");
        try {
            JSONObject lastItem = (JSONObject) itemsArray.get(itemsArray.size() - 1);
            return (int) Integer.parseInt(lastItem.get("id").toString());
        }
        catch (Exception e) {
            return 0;
        }
    }

    public static void deleteItemsJSON(int idItem) throws  Exception {
        JSONObject jsonObj = readItemsJSON("src/main/java/com/example/if2210_tb2_nge/database/Items.json");
        JSONArray itemsArray = (JSONArray) jsonObj.get("items");

        for (int i = 0; i < itemsArray.size(); i++) {
            JSONObject item = (JSONObject) itemsArray.get(i);
            if (Integer.parseInt(item.get("id").toString()) == idItem) {
                itemsArray.remove(i);
                break;
            }
        }

        byte[] jsonBytes = jsonObj.toJSONString().getBytes();
        Files.write(Paths.get("src/main/java/com/example/if2210_tb2_nge/database/Items.json"), jsonBytes, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.SYNC);
    }

    public static void updateItemsJSON(int idItem, String name, int price, int buyPrice,
                                                int stock, String category, String image) throws  Exception {
        JSONObject jsonObj = readItemsJSON("src/main/java/com/example/if2210_tb2_nge/database/Items.json");
        JSONArray itemsArray = (JSONArray) jsonObj.get("items");

        for (Object o : itemsArray) {
            JSONObject item = (JSONObject) o;
            if (Integer.parseInt(item.get("id").toString()) == idItem) {
                item.put("name", name);
                item.put("price", price);
                item.put("buyPrice", buyPrice);
                item.put("stock", stock);
                item.put("category", category);
                item.put("image", image);
                break;
            }
        }

        byte[] jsonBytes = jsonObj.toJSONString().getBytes();
        Files.write(Paths.get("src/main/java/com/example/if2210_tb2_nge/database/Items.json"), jsonBytes, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.SYNC);
    }

    public static void createItemsJSON() throws  Exception {
        JSONObject initialData = new JSONObject();
        JSONArray Items = new JSONArray();
        initialData.put("items", Items);

        // Write the initial database to a file
        byte[] jsonBytes = initialData.toJSONString().getBytes();
        Files.write(Paths.get("src/main/java/com/example/if2210_tb2_nge/database/Items.json"), jsonBytes, StandardOpenOption.CREATE, StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.SYNC);
    }

    public static JSONObject readItemsJSON(String filename) throws Exception {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(filename));
        JSONObject jsonObj = (JSONObject) obj;
        return jsonObj;
    }

    public static void addItems(String name, int price, int buyPrice,
                                                int stock, String category, String image) throws Exception {
        JSONObject jsonObj = readItemsJSON("src/main/java/com/example/if2210_tb2_nge/database/Items.json");
        JSONArray itemsArray = (JSONArray) jsonObj.get("items");
        // Create a new JSON object to represent the new product
        JSONObject product = new JSONObject();
        product.put("id", getLastIdItems() + 1);
        product.put("name", name);
        product.put("price", price);
        product.put("buyPrice", buyPrice);
        product.put("stock", stock);
        product.put("category", category);
        product.put("image", image);

        // Add the new product to the existing JSON data
        JSONArray Items = (JSONArray) jsonObj.get("items");
        Items.add(product);

        // Write the updated JSON data back to the file
        byte[] jsonBytes = jsonObj.toJSONString().getBytes();
        Files.write(Paths.get("src/main/java/com/example/if2210_tb2_nge/database/Items.json"), jsonBytes, StandardOpenOption.CREATE,
                StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.SYNC);
    }

    // make a function like select * from Items where stock is above x
//    public static JSONArray selectItemsWhereStockIsAbove(String filename, int stock) throws Exception {
//        // Read existing JSON data from file into a JSONObject
//        JSONParser parser = new JSONParser();
//        Object obj = parser.parse(new FileReader(filename));
//        JSONObject jsonObj = (JSONObject) obj;
//
//        // Get the Items array from the JSON object
//        JSONArray Items = (JSONArray) jsonObj.get("Items");
//
//        // Create a new JSON array to store the selected Items
//        JSONArray selectedItems = new JSONArray();
//
//        // Loop through the Items array and select the Items where stock is above x
//        for (Object productObj : Items) {
//            JSONObject product = (JSONObject) productObj;
//            if ((long) product.get("stock") > stock) {
//                selectedItems.add(product);
//            }
//        }
//
//        return selectedItems;
//    }

    public static void main(String[] args) throws Exception {
        // Read existing JSON data from file into a JSONObject
        JSONObject jsonObj = readItemsJSON("src/main/java/com/example/if2210_tb2_nge/database/Items.json");
        JSONArray itemsArray = (JSONArray) jsonObj.get("items");
        for (Object itemsObj : itemsArray) {
            JSONObject product = (JSONObject) itemsObj;
            System.out.println("id: " + product.get("id"));
            System.out.println("name: " + product.get("name"));
            System.out.println("price: " + product.get("price"));
            System.out.println("stock: " + product.get("stock"));
            System.out.println("buyPrice: " + product.get("buyPrice"));
            System.out.println("category: " + product.get("category"));
            System.out.println("image: " + product.get("image"));
        }

        // Delete product with id 2
//        System.out.println("Delete product with id 2");
//        deleteItemsJSON(23);
//        System.out.println("Select products where stock is above 7 and print the contents");
//        // Select products where stock is above 7 and print the contents
//        JSONArray selectedProducts = selectProductsWhereStockIsAbove("products.json", 7);
//        for (Object productObj : selectedProducts) {
//            JSONObject product = (JSONObject) productObj;
//            System.out.println("id: " + product.get("id"));
//            System.out.println("name: " + product.get("name"));
//            System.out.println("price: " + product.get("price"));
//            System.out.println("stock: " + product.get("stock"));
//            System.out.println();
//        }
    }
}

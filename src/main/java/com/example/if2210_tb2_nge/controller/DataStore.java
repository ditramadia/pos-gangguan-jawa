package com.example.if2210_tb2_nge.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class DataStore {

    /**
     * Write a JSONObject to a JSON file
     * @param context   context of the data store (name of the json file)
     * @param data      JSONObject of the data to be written
     */
    public static void writeFileJSON(String context, JSONObject data) throws Exception {
        byte[] jsonBytes = data.toJSONString().getBytes();
        Files.write(Paths.get("src/main/java/com/example/if2210_tb2_nge/database/" + context + ".json"), jsonBytes,
                StandardOpenOption.CREATE, StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.SYNC);
    }


    /**
     * Create a new empty JSON file for data store
     * @param context   context of the data store (name of the json file)
     */
    public static void createDataStoreJSON(String context) throws Exception {
        JSONObject initialData = new JSONObject();
        JSONArray dataStore = new JSONArray();
        initialData.put(context, dataStore);

        writeFileJSON(context, initialData);
    }

    /**
     * Initialize multiple empty data stores
     */
    public static void initDataStoreJSON() throws Exception {
        createDataStoreJSON("Items");
        createDataStoreJSON("Customers");
        createDataStoreJSON("Transactions");
    }

    /**
     * Read the data store JSON file
     * @return  JSONObject of the data store
     */
    public static JSONObject readDataStoreJSON(String context) throws Exception {
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader("src/main/java/com/example/if2210_tb2_nge/database/" + context + ".json");
        return (JSONObject) jsonParser.parse(reader);
    }

    /**
     * Add data to the data store JSON file
     * @param context   context of the data store (name of the json file)
     * @param data      JSONObject of the data to be added
     */
    public static void addDataStoreJSON(String context, JSONObject data) throws Exception {
        JSONObject jsonObj = readDataStoreJSON(context);
        JSONArray jsonArray = (JSONArray) jsonObj.get(context);
        data.put("id", getLastId(context) + 1);
        jsonArray.add(data);
        writeFileJSON(context, jsonObj);
    }

    /**
     * Update an existing data in the data store JSON file
     * @param context   context of the data store (name of the json file)
     * @param data      JSONObject of the new data
     * @param Id        id of the data to be updated
     */
    public static void updateDataStoreJSON(String context, JSONObject data, Integer Id) throws Exception {
        JSONObject jsonObj = readDataStoreJSON(context);
        JSONArray jsonArray = (JSONArray) jsonObj.get(context);
        for (Object o : jsonArray) {
            JSONObject dataObject = (JSONObject) o;
            Integer dataID = Integer.parseInt(dataObject.get("id").toString());
            if (dataID == Id) {
                data.put("id", Id);
                jsonArray.remove(dataObject);
                jsonArray.add(data);
                break;
            }
        }
        writeFileJSON(context, jsonObj);
    }

    /**
     * Delete an existing data in the data store JSON file
     * @param context   context of the data store (name of the json file)
     * @param Id        id of the data to be deleted
     */
    public static void deleteDataStoreJSON(String context, Integer Id) throws Exception {
        JSONObject jsonObj = readDataStoreJSON(context);
        JSONArray jsonArray = (JSONArray) jsonObj.get(context);
        for (Object o : jsonArray) {
            JSONObject dataObject = (JSONObject) o;
            Integer dataID = Integer.parseInt(dataObject.get("id").toString());
            if (dataID == Id) {
                jsonArray.remove(dataObject);
                break;
            }
        }
        writeFileJSON(context, jsonObj);
    }

    /**
     * Get the last id of the data store
     * @return last id of the data store
     */
    public static int getLastId(String context) throws Exception {
        JSONObject jsonObj = readDataStoreJSON(context);
        JSONArray jsonArray = (JSONArray) jsonObj.get(context);
        try {
            JSONObject lastData = (JSONObject) jsonArray.get(jsonArray.size() - 1);
            return Integer.parseInt(lastData.get("id").toString());
        } catch (Exception e) {
            return 0;
        }
    }

    public static void main(String[] args) throws Exception {
        initDataStoreJSON();

        JSONObject item = new JSONObject();
        item.put("name", "Buku");
        item.put("price", 10000);
        item.put("stock", 10);
        item.put("category", "Buku");
        item.put("image", "www.pornhub.com");
        addDataStoreJSON("Items", item);
        addDataStoreJSON("Items", item);

        JSONObject customer = new JSONObject();
        customer.put("name", "Budi");
        customer.put("noTelp", "08123456789");
        customer.put("vip", false);
        customer.put("active", true);
        addDataStoreJSON("Customers", customer);
        addDataStoreJSON("Customers", customer);

        JSONObject transaction = new JSONObject();
        transaction.put("customerId", 1);
        transaction.put("totalPrice", 10000);
        transaction.put("date", "2021-04-01");
        addDataStoreJSON("Transactions", transaction);

//        update items
        JSONObject itemUpdate = new JSONObject();
        itemUpdate.put("name", "Bukuuuu");
        itemUpdate.put("price", 10000);
        itemUpdate.put("stock", 10);
        itemUpdate.put("category", "Buku");
        itemUpdate.put("image", "www.pornhub.com");
        updateDataStoreJSON("Items", itemUpdate, 1);

//        delete customer
        deleteDataStoreJSON("Customers", 1);

        JSONObject tes = readDataStoreJSON("Items");
        JSONArray items = (JSONArray) tes.get("Items");
        for (Object o : items) {
            JSONObject data = (JSONObject) o;
            System.out.println(data.get("id"));
            System.out.println(data.get("name"));
            System.out.println(data.get("price"));
            System.out.println(data.get("stock"));
            System.out.println(data.get("category"));
            System.out.println(data.get("image"));
        }

        JSONObject tes1 = readDataStoreJSON("Customers");
        JSONArray customers = (JSONArray) tes1.get("Customers");
        for (Object o : customers) {
            JSONObject data = (JSONObject) o;
            System.out.println(data.get("id"));
            System.out.println(data.get("name"));
            System.out.println(data.get("noTelp"));
            System.out.println(data.get("vip"));
            System.out.println(data.get("active"));
        }
    }
}

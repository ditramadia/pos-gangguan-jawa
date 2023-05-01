package com.example.if2210_tb2_nge.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class DataStore {
    public static void createDataStoreJSON() throws Exception {
        JSONObject initialData = new JSONObject();
        JSONArray dataStore = new JSONArray();
        initialData.put("dataStore", dataStore);

        // items database
        JSONObject items = new JSONObject();
        JSONArray itemsArray = new JSONArray();
        items.put("items", itemsArray);
        dataStore.add(items);

        // customers database
        JSONObject customers = new JSONObject();
        JSONArray customersArray = new JSONArray();
        customers.put("customers", customersArray);
        dataStore.add(customers);

        // transactions database
        JSONObject transactions = new JSONObject();
        JSONArray transactionsArray = new JSONArray();
        transactions.put("transactions", transactionsArray);
        dataStore.add(transactions);

        byte[] jsonBytes = initialData.toJSONString().getBytes();
        Files.write(Paths.get("src/main/java/com/example/if2210_tb2_nge/database/DataStore.json"), jsonBytes, StandardOpenOption.CREATE, StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.SYNC);
    }

    public static JSONObject readDataStoreJSON() throws Exception {
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader("src/main/java/com/example/if2210_tb2_nge/database/DataStore.json");
        return (JSONObject) jsonParser.parse(reader);
    }

    public static JSONObject readSpecificJSON(String key) throws Exception {
        JSONObject jsonObj = readDataStoreJSON();
        JSONArray dataStore = (JSONArray) jsonObj.get("dataStore");
        for (Object o : dataStore) {
            JSONObject data = (JSONObject) o;
            if (data.containsKey(key)) {
                return data;
            }
        }
        return null;
    }

    public static void addDataStoreJSON(String key, JSONObject data) throws Exception {
        JSONObject jsonObj = readDataStoreJSON();
        JSONArray dataStore = (JSONArray) jsonObj.get("dataStore");
        for (Object o : dataStore) {
            JSONObject dataObject = (JSONObject) o;
            if (dataObject.containsKey(key)) {
                JSONArray array = (JSONArray) dataObject.get(key);
                data.put("id", getLastId(key) + 1);
                array.add(data);
                break;
            }
        }
        byte[] jsonBytes = jsonObj.toJSONString().getBytes();
        Files.write(Paths.get("src/main/java/com/example/if2210_tb2_nge/database/DataStore.json"), jsonBytes, StandardOpenOption.CREATE, StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.SYNC);
    }

    public static void updateDataStoreJSON(String key, JSONObject data, Integer Id) throws Exception {
        JSONObject jsonObj = readDataStoreJSON();
        JSONArray dataStore = (JSONArray) jsonObj.get("dataStore");
        for (Object o : dataStore) {
            JSONObject dataObject = (JSONObject) o;
            if (dataObject.containsKey(key)) {
                JSONArray array = (JSONArray) dataObject.get(key);
                for (Object o1 : array) {
                    JSONObject data1 = (JSONObject) o1;
                    Integer dataID = Integer.parseInt(data1.get("id").toString());
                    if (dataID == Id) {
                        data.put("id", Id);
                        array.remove(data1);
                        array.add(data);
                        break;
                    }
                }
                break;
            }
        }
        byte[] jsonBytes = jsonObj.toJSONString().getBytes();
        Files.write(Paths.get("src/main/java/com/example/if2210_tb2_nge/database/DataStore.json"), jsonBytes, StandardOpenOption.CREATE, StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.SYNC);
    }

    // delete data in database
    public static void deleteDataStoreJSON(String key, Integer Id) throws Exception {
        JSONObject jsonObj = readDataStoreJSON();
        JSONArray dataStore = (JSONArray) jsonObj.get("dataStore");
        for (Object o : dataStore) {
            JSONObject dataObject = (JSONObject) o;
            if (dataObject.containsKey(key)) {
                JSONArray array = (JSONArray) dataObject.get(key);
                for (Object o1 : array) {
                    JSONObject data1 = (JSONObject) o1;
                    Integer dataID = Integer.parseInt(data1.get("id").toString());
                    if (dataID == Id) {
                        array.remove(data1);
                        break;
                    }
                }
                break;
            }
        }
        byte[] jsonBytes = jsonObj.toJSONString().getBytes();
        Files.write(Paths.get("src/main/java/com/example/if2210_tb2_nge/database/DataStore.json"), jsonBytes, StandardOpenOption.CREATE, StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.SYNC);
    }

    public static int getLastId(String key) throws Exception {
        JSONObject jsonObj = readDataStoreJSON();
        JSONArray dataStore = (JSONArray) jsonObj.get("dataStore");
        for (Object o : dataStore) {
            JSONObject data = (JSONObject) o;
            if (data.containsKey(key)) {
                JSONArray array = (JSONArray) data.get(key);
                try {
                    JSONObject lastData = (JSONObject) array.get(array.size() - 1);
                    return Integer.parseInt(lastData.get("id").toString());
                } catch (Exception e) {
                    return 0;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws Exception {
        createDataStoreJSON();

        JSONObject item = new JSONObject();
        item.put("name", "Buku");
        item.put("price", 10000);
        item.put("stock", 10);
        item.put("category", "Buku");
        item.put("image", "www.pornhub.com");
        addDataStoreJSON("items", item);
        addDataStoreJSON("items", item);

        JSONObject customer = new JSONObject();
        customer.put("name", "Budi");
        customer.put("noTelp", "08123456789");
        customer.put("vip", false);
        customer.put("active", true);
        addDataStoreJSON("customers", customer);
        addDataStoreJSON("customers", customer);

        JSONObject transaction = new JSONObject();
        transaction.put("customerId", 1);
        transaction.put("totalPrice", 10000);
        transaction.put("date", "2021-04-01");
        addDataStoreJSON("transactions", transaction);

        //update items
        JSONObject itemUpdate = new JSONObject();
        itemUpdate.put("name", "Bukuuuu");
        itemUpdate.put("price", 10000);
        itemUpdate.put("stock", 10);
        itemUpdate.put("category", "Buku");
        itemUpdate.put("image", "www.pornhub.com");
        updateDataStoreJSON("items", itemUpdate, 1);

        //delete customer
        deleteDataStoreJSON("customers", 1);

        JSONObject tes = readSpecificJSON("items");
        JSONArray items = (JSONArray) tes.get("items");
        for (Object o : items) {
            JSONObject data = (JSONObject) o;
            System.out.println(data.get("id"));
            System.out.println(data.get("name"));
            System.out.println(data.get("price"));
            System.out.println(data.get("stock"));
            System.out.println(data.get("category"));
            System.out.println(data.get("image"));
        }

        JSONObject tes1 = readSpecificJSON("customers");
        JSONArray customers = (JSONArray) tes1.get("customers");
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

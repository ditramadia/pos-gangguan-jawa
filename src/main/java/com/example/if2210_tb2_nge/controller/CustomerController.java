package com.example.if2210_tb2_nge.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class CustomerController {
    public static void createCustomersJSON() throws  Exception {
        JSONObject initialData = new JSONObject();
        JSONArray Customers = new JSONArray();
        initialData.put("customers", Customers);

        // Write the initial database to a file
        byte[] jsonBytes = initialData.toJSONString().getBytes();
        Files.write(Paths.get("src/main/java/com/example/if2210_tb2_nge/database/Customers.json"), jsonBytes, StandardOpenOption.CREATE, StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.SYNC);
    }

    public static JSONObject readCustomersJSON(String filename) throws Exception {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(filename));
        JSONObject jsonObj = (JSONObject) obj;
        return jsonObj;
    }

    public static void addCustomers(String name, String noTelp, boolean vip,
                                    boolean active
//                                    List<Bill> history
    ) throws Exception {
        JSONObject jsonObj = readCustomersJSON("src/main/java/com/example/if2210_tb2_nge/database/Customers.json");
        JSONArray itemsArray = (JSONArray) jsonObj.get("customers");
        // Create a new JSON object to represent the new customers
        JSONObject customer = new JSONObject();
        customer.put("id", getLastIdCustomers() + 1);
        customer.put("name", name);
        customer.put("noTelp", noTelp);
        customer.put("vip", vip);
        customer.put("active", active);
//        customer.put("history", history);

        // Add the new customer to the existing JSON data
        JSONArray Items = (JSONArray) jsonObj.get("customers");
        Items.add(customer);

        // Write the updated JSON data back to the file
        byte[] jsonBytes = jsonObj.toJSONString().getBytes();
        Files.write(Paths.get("src/main/java/com/example/if2210_tb2_nge/database/Customers.json"), jsonBytes, StandardOpenOption.CREATE,
                StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.SYNC);
    }

    public static void updateCustomersJSON(int idCustomer, String name, String noTelp, boolean vip,
                                           boolean active) throws  Exception {
        JSONObject jsonObj = readCustomersJSON("src/main/java/com/example/if2210_tb2_nge/database/Customers.json");
        JSONArray itemsArray = (JSONArray) jsonObj.get("customers");

        for (Object o : itemsArray) {
            JSONObject item = (JSONObject) o;
            if (Integer.parseInt(item.get("id").toString()) == idCustomer) {
                item.put("name", name);
                item.put("noTelp", noTelp);
                item.put("vip", vip);
                item.put("active", active);
                break;
            }
        }

        byte[] jsonBytes = jsonObj.toJSONString().getBytes();
        Files.write(Paths.get("src/main/java/com/example/if2210_tb2_nge/database/Customers.json"), jsonBytes, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.SYNC);
    }

    public static void deleteItemsJSON(int idCustomer) throws  Exception {
        JSONObject jsonObj = readCustomersJSON("src/main/java/com/example/if2210_tb2_nge/database/Customers.json");
        JSONArray itemsArray = (JSONArray) jsonObj.get("customers");

        for (int i = 0; i < itemsArray.size(); i++) {
            JSONObject item = (JSONObject) itemsArray.get(i);
            if (Integer.parseInt(item.get("id").toString()) == idCustomer) {
                itemsArray.remove(i);
                break;
            }
        }

        byte[] jsonBytes = jsonObj.toJSONString().getBytes();
        Files.write(Paths.get("src/main/java/com/example/if2210_tb2_nge/database/Customers.json"), jsonBytes, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.SYNC);
    }

    public static int getLastIdCustomers() throws Exception {
        JSONObject jsonObj = readCustomersJSON("src/main/java/com/example/if2210_tb2_nge/database/Customers.json");
        JSONArray itemsArray = (JSONArray) jsonObj.get("customers");
        try {
            JSONObject lastItem = (JSONObject) itemsArray.get(itemsArray.size() - 1);
            return (int) Integer.parseInt(lastItem.get("id").toString());
        }
        catch (Exception e) {
            return 0;
        }
    }

    public static void main(String[] args) throws Exception {
        JSONObject jsonObj = readCustomersJSON("src/main/java/com/example/if2210_tb2_nge/database/Customers.json");
        JSONArray itemsArray = (JSONArray) jsonObj.get("customers");
        for (Object itemsObj : itemsArray) {
            JSONObject item = (JSONObject) itemsObj;
            System.out.println(item.get("id"));
            System.out.println(item.get("name"));
            System.out.println(item.get("noTelp"));
            System.out.println(item.get("vip"));
            System.out.println(item.get("active"));
        }
    }
}
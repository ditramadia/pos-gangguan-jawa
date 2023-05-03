package com.example.if2210_tb2_nge.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class DataStoreFactory {
    public static DataStore getDataStore(String fileName, String format) {
        switch (format.toLowerCase()) {
            case "json":
                return new JSONAdapter(fileName);
            case "xml":
                return new XMLAdapter(fileName);
            case "obj":
                return new OBJAdapter(fileName);
            default:
                throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }

    public static void main(String[] args) throws JsonProcessingException {
        String directory = "src/main/java/com/example/if2210_tb2_nge/database/";

        //get all files inside
        File folder = new File(directory);
        File[] listOfFiles = folder.listFiles();
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < listOfFiles.length; i++) {
            list.add(listOfFiles[i].getName());
        }

        //get all files with extension .json
        List<String> jsonFiles = new ArrayList<String>();
        for (String file : list) {
            if (file.endsWith(".json")) {
                jsonFiles.add(file);
                System.out.println(file);
            }
        }

        for (String file : jsonFiles) {
            String jsonFileName = directory + file;
            DataStore dataStore = DataStoreFactory.getDataStore(jsonFileName, "json");
            Object data = dataStore.load();
            // convert Object to String
            String json = new ObjectMapper().writeValueAsString(data);

            Gson gson = new Gson();
            Map<String, List<Map<String, Object>>> dataz = gson.fromJson(json, Map.class);
            try {
                List<Map<String, Object>> items = dataz.get("items");
                for (Map<String, Object> item : items) {
                    System.out.println(item.get("name"));
                }
            } catch (Exception e) {

            }
        }


        // INI EXPORT
//        for (String file : jsonFiles) {
//            String jsonFileName = directory + file;
//            // read the file, convert to xml, and save it
//            String xmlFileName = directory + file.substring(0, file.length() - 5) + ".xml";
//            String formatTo = "xml";
//            String formatFrom = "json";
//
//            // Load data from JSON file
//            DataStore dataStore = DataStoreFactory.getDataStore(jsonFileName, formatFrom);
//            Object data = dataStore.load();
//
//            // Save data to XML file
//            dataStore = DataStoreFactory.getDataStore(xmlFileName, formatTo);
//            dataStore.save(data);
//        }

//        for (S)




//        String xmlFileName = "src/main/java/com/example/if2210_tb2_nge/database/Itemss.json";
//        String jsonFileName = "src/main/java/com/example/if2210_tb2_nge/database/Items.xml";
//        String formatTo = "json";
//        String formatFrom = "xml";
//
//        // Load data from JSON file
//        DataStore dataStore = DataStoreFactory.getDataStore(jsonFileName, formatFrom);
//        Object data = dataStore.load();
//
//        System.out.println(data);

        // Save data to XML file
//        dataStore = DataStoreFactory.getDataStore(xmlFileName, formatTo);
//        dataStore.save(data);
    }
}

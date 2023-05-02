package com.example.if2210_tb2_nge.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

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

    public static void main(String[] args) {
        String xmlFileName = "src/main/java/com/example/if2210_tb2_nge/database/Itemss.json";
        String jsonFileName = "src/main/java/com/example/if2210_tb2_nge/database/Items.xml";
        String formatTo = "json";
        String formatFrom = "xml";

        // Load data from JSON file
        DataStore dataStore = DataStoreFactory.getDataStore(jsonFileName, formatFrom);
        Object data = dataStore.load();

        // Save data to XML file
        dataStore = DataStoreFactory.getDataStore(xmlFileName, formatTo);
        dataStore.save(data);
    }
}

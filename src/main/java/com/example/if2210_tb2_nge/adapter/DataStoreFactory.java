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
        String fileName = "src/main/java/com/example/if2210_tb2_nge/database/Items.json";
        String format = "json";
        DataStore dataStore = DataStoreFactory.getDataStore(fileName, format);

        // Baca data dari file
        Object obj = dataStore.load();

        // Cetak data
        System.out.println(obj);



    }
}

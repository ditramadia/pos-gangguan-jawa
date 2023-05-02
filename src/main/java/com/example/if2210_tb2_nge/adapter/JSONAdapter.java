package com.example.if2210_tb2_nge.adapter;

import java.io.*;
import java.nio.file.*;
import com.fasterxml.jackson.databind.*;

public class JSONAdapter implements DataStore {
    private Path path;
    private ObjectMapper objectMapper;

    public JSONAdapter(String fileName) {
        this.path = Paths.get(fileName);
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void save(Object obj) {
        try {
            objectMapper.writeValue(Files.newBufferedWriter(path), obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object load() {
        try {
            return objectMapper.readValue(Files.newBufferedReader(path), Object.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}


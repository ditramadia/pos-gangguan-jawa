package com.example.if2210_tb2_nge.adapter;

import java.io.*;
import java.nio.file.*;
import com.fasterxml.jackson.dataformat.xml.*;

public class XMLAdapter implements DataStore {
    private Path path;
    private XmlMapper xmlMapper;

    public XMLAdapter(String fileName) {
        this.path = Paths.get(fileName);
        this.xmlMapper = new XmlMapper();
    }

    @Override
    public void save(Object obj) {
        try {
            xmlMapper.writeValue(Files.newBufferedWriter(path), obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object load() {
        try {
            return xmlMapper.readValue(Files.newBufferedReader(path), Object.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}


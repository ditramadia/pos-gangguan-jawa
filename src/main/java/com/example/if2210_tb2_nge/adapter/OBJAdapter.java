package com.example.if2210_tb2_nge.adapter;

import java.io.*;
import java.nio.file.*;

public class OBJAdapter implements DataStore {
    private Path path;

    public OBJAdapter(String fileName) {
        this.path = Paths.get(fileName);
    }

    @Override
    public void save(Object obj) {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))) {
            oos.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object load() {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}


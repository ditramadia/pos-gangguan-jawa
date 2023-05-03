package com.example.if2210_tb2_nge.adapter;

import java.nio.file.Path;

public interface DataStore {
    void save(Object obj);
    void save(Object obj, Path path);
    Object load();
}
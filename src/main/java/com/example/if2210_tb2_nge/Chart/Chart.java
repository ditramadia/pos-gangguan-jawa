package com.example.if2210_tb2_nge.Chart

public interface Chart{
    public void addData(String label, List<Integer> data, List<String> xData);
    public void addData(String label, List<Integer> data, List<String> xData, List<String> yData);
    public void show();
}
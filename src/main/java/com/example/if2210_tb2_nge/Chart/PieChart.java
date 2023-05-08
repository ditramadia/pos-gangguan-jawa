package com.example.if2210_tb2_nge.chart;

import java.util.List;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class PieChart implements Chart, Runnable {
    private DefaultPieDataset dataset;
    private String title;
    private ChartPanel chartPanel;
    private JFrame frame;

    public PieChart(String title) {
        this.title = title;
        this.dataset = new DefaultPieDataset();
        this.chartPanel = new ChartPanel(null);
        this.frame = new JFrame(title);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.add(chartPanel);
        this.frame.pack();
        this.frame.setVisible(true);
    }

    public void addData(String label, int value) {
        this.dataset.setValue(label, value);
    }

    public void addData(List<String> labels, List<Integer> values) {
        if (labels.size() != values.size()) {
            throw new IllegalArgumentException("Number of labels and values must match");
        }
        for (int i = 0; i < labels.size(); i++) {
            this.dataset.setValue(labels.get(i), values.get(i));
        }
    }

    public void show() {
        JFreeChart chart = ChartFactory.createPieChart(this.title, this.dataset, true, true, false);
        this.chartPanel.setChart(chart);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.show();
        }
    }
}

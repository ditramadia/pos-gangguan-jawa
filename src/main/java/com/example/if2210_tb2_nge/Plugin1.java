package com.example.if2210_tb2_nge;


import com.example.if2210_tb2_nge.entity.Items;
import com.example.if2210_tb2_nge.plugin.BasePlugin;
import com.example.if2210_tb2_nge.repository.DataProvider;
import javafx.scene.layout.BorderPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Plugin1 extends BasePlugin {
    private static List<Items> itemsList = DataProvider.getItemList();

    private static Map<String, Integer> initMap() {
        Map<String, Integer> map = new HashMap<>();
        for (Items item : itemsList) {
            if (map.containsKey(item.getCategory())) {
                map.put(item.getCategory(), map.get(item.getCategory()) + item.getPrice());
            } else {
                map.put(item.getCategory(), item.getPrice());
            }
        }
        return map;
    }
    private Integer sumOfBuyPrice() {
        int sum = 0;
        for (Items item : itemsList) {
            sum += item.getBuyPrice();
        }
        return sum;
    }
    private Integer sumOfSalePrice() {
        int sum = 0;
        for (Items item : itemsList) {
            sum += item.getPrice();
        }
        return sum;
    }

    /**
     * Returns a sample dataset.
     *
     * @return The dataset.
     */
    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<String, Integer> map = initMap();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            dataset.addValue(entry.getValue(), "price", entry.getKey());
        }
        return dataset;
    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Sum Price of All Items Based On Their Category", null /* x-axis label*/,
                "Price" /* y-axis label */, dataset);

        chart.setBackgroundPaint(Color.white);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        chart.getLegend().setFrame(BlockBorder.NONE);
        return chart;
    }

    public void run() {
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartViewer viewer = new ChartViewer(chart);
        BorderPane layout = new BorderPane();
        layout.setCenter(viewer);
        tab.setContent(layout);
    }



}

package com.example.if2210_tb2_nge;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.embed.swing.SwingNode;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.PopupWindow;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.awt.*;
import java.io.IOException;
import javafx.animation.*;
import javafx.util.*;
import javafx.util.Duration;
import java.time.*;
import java.time.format.DateTimeFormatter;
import javafx.geometry.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.List;


public class HelloApplication extends Application {
    Button button;
    Clock clock;
    HomePage homePage;
    MenuPage menu;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("JAVAAAAAA");
        BorderPane root = new BorderPane();

        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File");        Menu settings = new Menu ("Settings");


        menuBar.setUseSystemMenuBar(true);

        menuBar.getMenus().addAll(file,settings);

        MenuItem item1 = new MenuItem("Open");
        MenuItem item2 = new MenuItem("Save");
        MenuItem item3 = new MenuItem("Exit");

        file.getItems().addAll(item1,item2,item3);
        root.setTop(menuBar);

        TabPane tabPane = new TabPane();
        Tab tab1 = new Tab("Tab 1");
        BorderPane layouttab1 = new BorderPane();
//
        homePage = new HomePage();
        menu = new MenuPage();
//
//
        tab1.setContent(layouttab1);
//
        tab1.setClosable(false);
//
        VBox sideMenu = new VBox();
        Button button1 = new Button("Button 1");
        Button button2 = new Button("Button 2");
        Button button3 = new Button("Button 3");
        sideMenu.getChildren().addAll(button1,button2,button3);
        sideMenu.setStyle("-fx-background-color: #ADD8E6;");
        sideMenu.setAlignment(Pos.TOP_LEFT);
        sideMenu.setPrefWidth(200);
        layouttab1.setLeft(sideMenu);
        StackPane mulscreen = new StackPane();

        mulscreen.getChildren().add(homePage.getlayout());
        mulscreen.getChildren().add(menu.getlayout());
        button1.setOnAction(e -> {
            mulscreen.getChildren().get(0).setVisible(true);
            mulscreen.getChildren().get(1).setVisible(false);
        });
        button2.setOnAction(e -> {
            mulscreen.getChildren().get(1).setVisible(true);
            mulscreen.getChildren().get(0).setVisible(false);
        });
        layouttab1.setCenter(mulscreen);
        mulscreen.getChildren().get(1).setVisible(false);
//
//
        tabPane.getTabs().add(tab1);



        Tab newTab = new Tab("+");
        tabPane.getTabs().add(newTab);

        newTab.setOnSelectionChanged(e-> {
            Tab tab = new Tab("New Tab");
            tabPane.getTabs().add(tabPane.getTabs().size()-1,tab);
            tabPane.getSelectionModel().select(tabPane.getTabs().size()-2);
            System.out.println(tabPane.getTabs().size()-1);
        });

        root.setCenter(tabPane);
        Scene scene = new Scene(root, 300, 250);
        stage.setScene(scene);
        stage.show();
    }

    public static JSONObject readJsonFile(String filename) throws Exception {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(filename));
        JSONObject jsonObj = (JSONObject) obj;
        return jsonObj;
    }
    public static void addProductToJsonDatabase(String filename, int id, String name, int price, int buyPrice,
                                                int stock, String category, String image) throws Exception {
        // Read existing JSON data from file into a JSONObject
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(filename));
        JSONObject jsonObj = (JSONObject) obj;

        // Create a new JSON object to represent the new product
        JSONObject product = new JSONObject();
        product.put("id", id);
        product.put("name", name);
        product.put("price", price);
        product.put("buyPrice", buyPrice);
        product.put("stock", stock);
        product.put("category", category);
        product.put("image", image);

        // Add the new product to the existing JSON data
        JSONArray products = (JSONArray) jsonObj.get("products");
        products.add(product);

        // Write the updated JSON data back to the file
        byte[] jsonBytes = jsonObj.toJSONString().getBytes();
        Files.write(Paths.get(filename), jsonBytes, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.SYNC);
    }

    // make a function like select * from products where stock is above x
    public static JSONArray selectProductsWhereStockIsAbove(String filename, int stock) throws Exception {
        // Read existing JSON data from file into a JSONObject
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(filename));
        JSONObject jsonObj = (JSONObject) obj;

        // Get the products array from the JSON object
        JSONArray products = (JSONArray) jsonObj.get("products");

        // Create a new JSON array to store the selected products
        JSONArray selectedProducts = new JSONArray();

        // Loop through the products array and select the products where stock is above x
        for (Object productObj : products) {
            JSONObject product = (JSONObject) productObj;
            if ((long) product.get("stock") > stock) {
                selectedProducts.add(product);
            }
        }

        return selectedProducts;
    }

    public static void main(String[] args) throws Exception {
        // Create a new JSON object to represent the initial database
        JSONObject initialData = new JSONObject();
        JSONArray products = new JSONArray();
        initialData.put("products", products);

        // Write the initial database to a file
        byte[] jsonBytes = initialData.toJSONString().getBytes();
        Files.write(Paths.get("products.json"), jsonBytes, StandardOpenOption.CREATE, StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.SYNC);

        // Add some products to the database
        addProductToJsonDatabase("products.json", 1, "Kulkas", 1000000, 800000, 5, "elektronik", "");
        addProductToJsonDatabase("products.json", 2, "Lemari", 100000, 90000, 10, "perabotan", "");
        addProductToJsonDatabase("products.json", 3, "Meja", 80000, 75000, 50,"perabotan", "");
        addProductToJsonDatabase("products.json", 4, "Kursi", 50000, 45000, 60,"perabotan", "");
        addProductToJsonDatabase("products.json", 5, "Indomie", 3000, 2900, 100, "makanan", "");
        addProductToJsonDatabase("products.json", 6, "Roti tawar", 18000, 15000, 20,"makanan", "");
        addProductToJsonDatabase("products.json", 7, "Susu", 15000, 10000, 20,"minuman", "");
        addProductToJsonDatabase("products.json", 8, "Kopi", 5000, 3000, 50,"minuman", "");
        addProductToJsonDatabase("products.json", 9, "Sabun", 10000, 5000, 50,"alat mandi", "");
        addProductToJsonDatabase("products.json", 10, "Shampoo", 8000, 5000, 50,"alat mandi", "");
        addProductToJsonDatabase("products.json", 11, "Pasta gigi", 20000, 18000, 50,"alat mandi", "");
        addProductToJsonDatabase("products.json", 12, "Conditioner", 10000, 5000, 50,"alat mandi", "");
        addProductToJsonDatabase("products.json", 13, "Pisang", 5000, 3000, 20,"buah", "");
        addProductToJsonDatabase("products.json", 14, "Apel", 5000, 3000, 20,"buah", "");
        addProductToJsonDatabase("products.json", 15, "Jeruk", 5000, 3000, 20,"buah", "");
        addProductToJsonDatabase("products.json", 16, "Mangga", 5000, 3000, 20,"buah", "");
        addProductToJsonDatabase("products.json", 17, "Baju", 50000, 40000, 10,"pakaian", "");
        addProductToJsonDatabase("products.json", 18, "Celana", 40000, 35000, 10,"pakaian", "");
        addProductToJsonDatabase("products.json", 19, "Jaket", 100000, 90000, 10,"pakaian", "");
        addProductToJsonDatabase("products.json", 20, "Sepatu", 200000, 150000, 5,"pakaian", "");
        addProductToJsonDatabase("products.json", 21, "Tolak angin", 3000, 2500, 30,"obat", "");
        addProductToJsonDatabase("products.json", 22, "Microlax", 7000, 5000, 15,"obat", "");
        addProductToJsonDatabase("products.json", 23, "Betadine", 10000, 8000, 20,"obat", "");
        addProductToJsonDatabase("products.json", 24, "Entrostop", 10000, 9000, 20,"obat", "");



        // Read the database from the file and print the contents
//        JSONObject jsonObj = readJsonFile("products.json");
//        JSONArray productsArray = (JSONArray) jsonObj.get("products");
//        for (Object productObj : productsArray) {
//            JSONObject product = (JSONObject) productObj;
//            System.out.println("id: " + product.get("id"));
//            System.out.println("name: " + product.get("name"));
//            System.out.println("price: " + product.get("price"));
//            System.out.println("stock: " + product.get("stock"));
//            System.out.println("buy price: " + product.get("buyPrice");
//            System.out.println("category: " + product.get("category"));
//            System.out.println("image: " + product.get("image"));
//        }
//
//        System.out.println("Select products where stock is above 7 and print the contents");
//        // Select products where stock is above 7 and print the contents
//        JSONArray selectedProducts = selectProductsWhereStockIsAbove("products.json", 7);
//        for (Object productObj : selectedProducts) {
//            JSONObject product = (JSONObject) productObj;
//            System.out.println("id: " + product.get("id"));
//            System.out.println("name: " + product.get("name"));
//            System.out.println("price: " + product.get("price"));
//            System.out.println("stock: " + product.get("stock"));
//            System.out.println();
//        }

        launch();
    }
}


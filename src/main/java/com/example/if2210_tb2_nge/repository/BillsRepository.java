package com.example.if2210_tb2_nge.repository;

import com.example.if2210_tb2_nge.entity.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.if2210_tb2_nge.repository.ItemsRepository.getItemsById;

public class BillsRepository {
    private static List<Bill> bills;

    //    public static void setBillsRepository(Object obj) throws JsonProcessingException {
//        String json = null;
//        json = new ObjectMapper().writeValueAsString(obj);
//        Gson gson = new Gson();
//        Map<String, List<Map<String, Object>>> data = gson.fromJson(json, Map.class);
//        List<Map<String, Object>> billList = data.get("bills");
//        if (billList == null) {
//        }
//        else {
//            bills = new ArrayList<>();
//            for (Map<String, Object> bill : billList) {
//
//
//            }
//        }
//    }
//}
    public static void setBillsRepository(Object obj) throws Exception {
        String json = null;
        json = new ObjectMapper().writeValueAsString(obj);
        Gson gson = new Gson();
        Map<String, List<Map<String, Object>>> data = gson.fromJson(json, Map.class);
        List<Map<String, Object>> billsList = data.get("bills");
        if (billsList == null) {
        } else {
            bills = new ArrayList<>();
            for (Map<String, Object> bill : billsList) {
                Double customerId;
                try {
                    customerId = (Double) bill.get("customer_id");
                } catch (Exception e) {
                    customerId = Double.parseDouble((String) bill.get("customer_id"));
                }
                List<Map<String, Object>> cartList = (List<Map<String, Object>>) bill.get("cart");
                List<CartItem> cart = new ArrayList<>();
                for (Map<String, Object> item : cartList) {
                    Double itemId;
                    try {
                        itemId = (Double) item.get("item_id");
                    } catch (Exception e) {
                        itemId = Double.parseDouble((String) item.get("item_id"));
                    }
                    Double qty;
                    try {
                        qty = (Double) item.get("qty");
                    } catch (Exception e) {
                        qty = Double.parseDouble((String) item.get("qty"));
                    }
                    CartItem cartItem = new CartItem(getItemsById(itemId.intValue()), qty.intValue());
                    cart.add(cartItem);
                }
                Double subtotal;
                try {
                    subtotal = (Double) bill.get("subtotal");
                } catch (Exception e) {
                    subtotal = Double.parseDouble((String) bill.get("subtotal"));
                }
                Double discount;
                try {
                    discount = (Double) bill.get("discount");
                } catch (Exception e) {
                    discount = Double.parseDouble((String) bill.get("discount"));
                }
                Integer points;
                try {
                    points = (Integer) bill.get("points");
                } catch (Exception e) {
                    points = Integer.parseInt((String) bill.get("points"));
                }
                Double total;
                try {
                    total = (Double) bill.get("total");
                } catch (Exception e) {
                    total = Double.parseDouble((String) bill.get("total"));
                }
                FixedBill newBill = new FixedBill(cart, subtotal, discount, points, total);
                bills.add(newBill);
            }
        }
    }
}


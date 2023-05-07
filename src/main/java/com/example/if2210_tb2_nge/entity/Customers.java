package com.example.if2210_tb2_nge.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

public class Customers extends Guests {

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String noTelp;
    @Getter
    @Setter
    private Integer points;
    @Getter
    @Setter
    private Boolean vip;
    @Getter
    @Setter
    private Boolean active;

    public Customers( Integer id,String name, String noTelp, Integer points, Boolean vip, Boolean active) {
        super(id);
        this.name = name;
        this.noTelp = noTelp;
        this.points = points;
        this.vip = vip;
        this.active = active;
    }
}
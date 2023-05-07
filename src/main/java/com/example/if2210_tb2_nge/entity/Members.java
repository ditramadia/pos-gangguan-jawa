package com.example.if2210_tb2_nge.entity;

import lombok.Getter;
import lombok.Setter;

public class Members extends Customers {

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

    public Members(Integer id, String name, String noTelp, Integer points, Boolean vip, Boolean active) {
        super(id);
        this.name = name;
        this.noTelp = noTelp;
        this.points = points;
        this.vip = vip;
        this.active = active;
    }
}
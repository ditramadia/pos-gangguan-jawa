package com.example.if2210_tb2_nge.entity;

import lombok.Getter;
import lombok.Setter;

public class Customers {

    @Getter
    @Setter
    private Integer id;

    public Customers(Integer id) {
        this.id = id;
    }

    public String getName() {return "";}

    public void setName(String name) {}
    public String getNoTelp() {return "";}
    public void setNoTelp(String noTelp) {}
    public Integer getPoints() {return -1;}
    public void setPoints(Integer points) {}
    public Boolean getVip() {return false;}
    public void setVip(Boolean isVip) {}
    public Boolean getActive() {return false;}
    public void setActive(Boolean isActive) {}
}

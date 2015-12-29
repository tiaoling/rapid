package com.xiaojiuwo.models;

import com.xiaojiuwo.BaseModel;

import java.util.List;

/**
 * Created by liuhaibao on 15/10/31.
 */
@SuppressWarnings("rawtypes")
public class Commission extends BaseModel {

    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
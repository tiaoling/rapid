package com.xiaojiuwo.models;

import com.xiaojiuwo.BaseModel;

import java.util.List;

/**
 * Created by liuhaibao on 15/10/31.
 */
@SuppressWarnings("rawtypes")
public class AreaResult extends BaseModel {

    private String label;
    private String code;

    private double price;


    private int status;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


}
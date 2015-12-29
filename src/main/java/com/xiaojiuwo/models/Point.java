package com.xiaojiuwo.models;

import com.xiaojiuwo.BaseModel;

/**
 * Created by liuhaibao on 15/10/31.
 */
@SuppressWarnings("rawtypes")
public class Point extends BaseModel {


    private double longitude;
    private double lantitude;

    private long pointableId;
    private String pointableType;


    public long getPointableId() {
        return pointableId;
    }

    public void setPointableId(long pointableId) {
        this.pointableId = pointableId;
    }

    public String getPointableType() {
        return pointableType;
    }

    public void setPointableType(String pointableType) {
        this.pointableType = pointableType;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLantitude() {
        return lantitude;
    }

    public void setLantitude(double lantitude) {
        this.lantitude = lantitude;
    }



}

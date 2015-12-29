package com.xiaojiuwo.models;

import com.xiaojiuwo.BaseModel;

/**
 * Created by liuhaibao on 15/10/31.
 */
@SuppressWarnings("rawtypes")
public class Station extends BaseModel {

    private String name;
    private String address;
    private double lat;
    private double lng;
    private String cityName;
    private long stationableId;
    private String stationableType;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getStationableId() {
        return stationableId;
    }

    public void setStationableId(long stationableId) {
        this.stationableId = stationableId;
    }

    public String getStationableType() {
        return stationableType;
    }

    public void setStationableType(String stationableType) {
        this.stationableType = stationableType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

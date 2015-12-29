package com.xiaojiuwo.models;

import com.xiaojiuwo.BaseModel;

import java.util.Date;
import java.util.List;

/**
 * Created by liuhaibao on 15/10/31.
 */
@SuppressWarnings("rawtypes")
public class Area extends BaseModel {

    private String label;

    private double price;

    private String stationName;

    private String code;

    private long stationId;

    private long commissionId;



    private double distance;

    private double mian;

    private double latitude;

    private double longitude;

    public long getStationId() {
        return stationId;
    }

    public void setStationId(long stationId) {
        this.stationId = stationId;
    }

    public long getCommissionId() {
        return commissionId;
    }

    public void setCommissionId(long commissionId) {
        this.commissionId = commissionId;
    }



    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getMian() {
        return mian;
    }

    public void setMian(double mian) {
        this.mian = mian;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean includePoint(double lat, double lng, List<Point> points){
        boolean flag = false;
        boolean on = false;

        int l = points.size();
        int j = l - 1;

        double x;
        for(int i =0; i< points.size(); i++){
            Point pointx = points.get(i);
            Point pointt = points.get(j);
            double sx  =  pointx.getLantitude();
            double sy  =  pointx.getLongitude();

            double tx  = pointt.getLantitude();
            double ty  = pointt.getLongitude();

            if((sx == lat && sy == lng) || (tx == lat && ty == lng)){
                on = true;
                break;
            }
            if((sy < lng && ty >= lng) || (sy >= lng && ty < lng)){
                x = sx + (lng - sy) * (tx - sx) / (ty - sy);
                if(x == lat){
                    on = true;
                    break;
                }
                if(x > lat){
                    flag = !flag;
                }
            }
            j = i;
        }

        if(on){
            return on;
        }
        return flag;
    }
}
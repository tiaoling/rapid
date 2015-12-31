package com.xiaojiuwo.controllers;

import com.alibaba.fastjson.JSON;
import com.xiaojiuwo.models.Point;
import com.xiaojiuwo.models.Station;
import com.xiaojiuwo.services.StationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by liuhaibao on 15/10/31.
 */
@RestController
@RequestMapping("/stations")
public class StationsController{

    @Autowired
    StationsService stationsService;

    @RequestMapping(value={"","/"})
    public String index(){
        List<Station> stations = (List<Station>)stationsService.getAllStationsAndLoaction();
        String jsonString = JSON.toJSONString(stations);
        return jsonString;
    }

    @RequestMapping(value={"{id}"})
    public String station(@PathVariable long id){
        Station station = stationsService.findStationById(id);
        String jsonString = JSON.toJSONString(station);
        return jsonString;
    }

    @RequestMapping(value={"{id}/points"})
    public String points(@PathVariable long id){
        List<Point> points = (List<Point>)stationsService.findStationPointsById(id);
        String jsonString = JSON.toJSONString(points);
        return jsonString;
    }
}

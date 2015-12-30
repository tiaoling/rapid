package com.xiaojiuwo.controller;

import com.alibaba.fastjson.JSON;
import com.xiaojiuwo.models.Area;
import com.xiaojiuwo.models.AreaResult;
import com.xiaojiuwo.models.Point;
import com.xiaojiuwo.models.Station;
import com.xiaojiuwo.services.StationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by liuhaibao on 15/10/31.
 */
@RestController
@RequestMapping("/areas")
public class AreasController {



    @Autowired
    StationsService stationsService;


    @RequestMapping(value={"/price"})
    public String station(@RequestParam long station_id, @RequestParam String order_code, @RequestParam double lantitude, @RequestParam double longitude){

        boolean in = false;
        List<Point> points;
        AreaResult result = new AreaResult();

        if(in == false){
            result.setPrice(-1);
            result.setStatus(3);
            result.setMessage("此点所在坐标不在站点内");
        }
        String jsonString = JSON.toJSONString(result);
        return jsonString;
    }



}

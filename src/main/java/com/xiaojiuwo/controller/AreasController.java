package com.xiaojiuwo.controller;

import com.alibaba.fastjson.JSON;
import com.xiaojiuwo.models.Area;
import com.xiaojiuwo.models.AreaResult;
import com.xiaojiuwo.models.Point;
import com.xiaojiuwo.models.Station;
import com.xiaojiuwo.services.AreasService;
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
    AreasService areasService;

    @Autowired
    StationsService stationsService;


    @RequestMapping(value={"/price"})
    public String station(@RequestParam long station_id, @RequestParam String order_code, @RequestParam double lantitude, @RequestParam double longitude){
        Station station = stationsService.findStationFromMysql(station_id);
        List<Area> areas = areasService.findStationAreas(station_id);
        boolean in = false;
        List<Point> points;
        AreaResult result = new AreaResult();

        if(station == null){
            result.setMessage("此站点不存在");
            result.setStatus(1);
            result.setPrice(-1);
            String jsonString = JSON.toJSONString(result);
            return jsonString;
        }
        if(areas.size() == 0){
            result.setMessage("此站点没有提成区域");
            result.setStatus(2);
            result.setPrice(-1);
            String jsonString = JSON.toJSONString(result);
            return jsonString;
        }
        for(Area area : areas){
            points = areasService.findAreaPoint(area.getId());
            in = area.includePoint(lantitude,longitude,points);
            if(in){
                result.setId(area.getId());
                result.setCode(area.getCode());
                result.setStatus(0);
                result.setPrice(area.getPrice());
                result.setLabel(area.getLabel());
                result.setMessage("提成返回成功");

                break;
            }
        }
        if(in == false){
            result.setPrice(-1);
            result.setStatus(3);
            result.setMessage("此点所在坐标不在站点内");
        }
        String jsonString = JSON.toJSONString(result);
        return jsonString;
    }


    public AreasService getAreasService() {
        return areasService;
    }

    public void setAreasService(AreasService areasService) {
        this.areasService = areasService;
    }


}

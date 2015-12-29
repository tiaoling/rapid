package com.xiaojiuwo.services;

import com.xiaojiuwo.models.Area;
import com.xiaojiuwo.models.Point;
import com.xiaojiuwo.models.Station;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuhaibao on 15/12/1.
 */
@Service
@Transactional
public class AreasService  extends BaseService<Station> {

    public List<Area> findStationAreas(long id){
        Map params = new HashMap<String, Long>();
        params.put("stationId",id);
        return this.getMyBatisGeneralMySqlDao().getSqlSession().selectList("com.xiaojiuwo.models.mapper.AreasMapper.findSataionAreas", params);
    }

    public List<Point> findAreaPoint(long id){
        Map params = new HashMap<String, Long>();
        params.put("areaId",id);
        return this.getMyBatisGeneralMySqlDao().getSqlSession().selectList("com.xiaojiuwo.models.mapper.AreasMapper.findAreaPoints", params);
    }


    public int save(Area area){
        return this.getMyBatisGeneralMySqlDao().getSqlSession().insert("com.xiaojiuwo.models.mapper.AreasMapper.insert", area);
    }

    public int update(Area area){

        return this.getMyBatisGeneralMySqlDao().getSqlSession().update("com.xiaojiuwo.models.mapper.AreasMapper.update", area);
    }

    public int delete(long id){
        Map params = new HashMap<String, Long>();
        params.put("id",id);
        return this.getMyBatisGeneralMySqlDao().getSqlSession().delete("com.xiaojiuwo.models.mapper.AreasMapper.delete",id);
    }
}

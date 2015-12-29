package com.xiaojiuwo.services;

import com.xiaojiuwo.models.City;
import com.xiaojiuwo.models.Point;
import com.xiaojiuwo.models.Province;
import com.xiaojiuwo.models.Station;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuhaibao on 15/10/31.
 */
@Service
@Transactional
public class StationsService extends BaseService<Station>{

    public List<City> getCitiesByProvinceId(long provinceId){
        Map<String,Long> params = new HashMap<String,Long>();
        params.put("provinceId",provinceId);
        return myBatisGeneralDao.getSqlSession().selectList("com.xiaojiuwo.models.mapper.StationsMapper.findCitiesByProvinceId", params);
    }
    public List<Station> getStationsByCityId(long cityId){
        Map<String,Long> params = new HashMap<String,Long>();
        params.put("cityId",cityId);
        return myBatisGeneralDao.getSqlSession().selectList("com.xiaojiuwo.models.mapper.StationsMapper.findStationsByCityId", params);
    }

    public List<Station> getAllStations(){

        return myBatisGeneralDao.getSqlSession().selectList("com.xiaojiuwo.models.mapper.StationsMapper.findAllStations");
    }

    public List<Point>  findStationPointsById(long id){
        return myBatisGeneralDao.getSqlSession().selectList("com.xiaojiuwo.models.mapper.StationsMapper.findPointsByStationId",id);
    }
    public List<Station> getAllStationsAndLoaction(){

        return myBatisGeneralDao.getSqlSession().selectList("com.xiaojiuwo.models.mapper.StationsMapper.findAllStationsAndLatLng");
    }

    public Station findStationById(long id){

        return myBatisGeneralDao.getSqlSession().selectOne("com.xiaojiuwo.models.mapper.StationsMapper.findStationById", id);
    }

    public int save(Station station){
        return myBatisGeneralMySqlDao.getSqlSession().insert("com.xiaojiuwo.models.mapper.StationsMapper.insert", station);
    }

    public int delete(long id){
        return myBatisGeneralMySqlDao.getSqlSession().delete("com.xiaojiuwo.models.mapper.StationsMapper.delete", id);
    }

    public Station findStationFromMysql(long id){
        return myBatisGeneralMySqlDao.getSqlSession().selectOne("com.xiaojiuwo.models.mapper.StationsMapper.findStationFromMysql", id);
    }
}

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
public class PointsService extends BaseService<Point> {




    public int save(Point point){
        return this.getMyBatisGeneralMySqlDao().getSqlSession().insert("com.xiaojiuwo.models.mapper.PointsMapper.insert", point);
    }

    public int delete(long id){
        Map params = new HashMap<String, Long>();
        params.put("id",id);
        return this.getMyBatisGeneralMySqlDao().getSqlSession().delete("com.xiaojiuwo.models.mapper.PointsMapper.delete", params);
    }
    public int deleteByPointableId(long id){
        Map params = new HashMap<String, Long>();
        params.put("pointableId",id);
        return this.getMyBatisGeneralMySqlDao().getSqlSession().delete("com.xiaojiuwo.models.mapper.PointsMapper.deleteAreaPoints",id);
    }
}

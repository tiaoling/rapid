package com.xiaojiuwo.services;

import com.xiaojiuwo.models.Commission;
import com.xiaojiuwo.models.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuhaibao on 15/12/1.
 */
@Service
@Transactional
public class CommissionsService extends BaseService<Commission> {




    public int save(Commission commission){
        return this.getMyBatisGeneralMySqlDao().getSqlSession().insert("com.xiaojiuwo.models.mapper.CommissionsMapper.insert", commission);
    }

    public int delete(long id){

        return this.getMyBatisGeneralMySqlDao().getSqlSession().delete("com.xiaojiuwo.models.mapper.CommissionsMapper.delete", id);
    }

}

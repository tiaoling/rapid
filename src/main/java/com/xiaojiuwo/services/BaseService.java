package com.xiaojiuwo.services;

import com.xiaojiuwo.dao.GeneralRepository;
import com.xiaojiuwo.dao.MyBatisGeneralDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by liuhaibao on 15/10/31.
 */
@Service("baseService")
@Transactional
public class BaseService<T> {


    @Resource(name = "myBatisGeneralDao")
    protected MyBatisGeneralDao myBatisGeneralDao;

    @Autowired
    protected GeneralRepository generalRepository;

    public MyBatisGeneralDao getMyBatisGeneralDao() {
        return myBatisGeneralDao;
    }

    public void setMyBatisGeneralDao(MyBatisGeneralDao myBatisGeneralDao) {
        this.myBatisGeneralDao = myBatisGeneralDao;
    }

}
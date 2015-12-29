package com.xiaojiuwo.services;

import com.xiaojiuwo.dao.MyBatisGeneralDao;
import com.xiaojiuwo.dao.MyBatisGeneralMySqlDao;
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


    @Resource(name = "myBatisGeneralMySqlDao")
    protected MyBatisGeneralMySqlDao myBatisGeneralMySqlDao;



    public MyBatisGeneralMySqlDao getMyBatisGeneralMySqlDao() {
        return myBatisGeneralMySqlDao;
    }

    public void setMyBatisGeneralMySqlDao(MyBatisGeneralMySqlDao myBatisGeneralMySqlDao) {
        this.myBatisGeneralMySqlDao = myBatisGeneralMySqlDao;
    }

    public MyBatisGeneralDao getMyBatisGeneralDao() {
        return myBatisGeneralDao;
    }

    public void setMyBatisGeneralDao(MyBatisGeneralDao myBatisGeneralDao) {
        this.myBatisGeneralDao = myBatisGeneralDao;
    }

}
package com.xiaojiuwo.services;


import com.xiaojiuwo.repositories.MyBatisGeneralRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by liuhaibao on 15/10/31.
 */
@Service("baseService")
@Transactional
public class BaseService<T> {


    @Resource(name = "myBatisGeneralRepository")
    protected MyBatisGeneralRepository myBatisGeneralRepository;


    public MyBatisGeneralRepository getMyBatisGeneralRepository() {
        return myBatisGeneralRepository;
    }

    public void setMyBatisGeneralRepository(MyBatisGeneralRepository myBatisGeneralRepository) {
        this.myBatisGeneralRepository = myBatisGeneralRepository;
    }

}
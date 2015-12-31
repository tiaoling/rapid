package com.xiaojiuwo.services;

import com.xiaojiuwo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by liuhaibao on 15/12/31.
 */
@Service
@Transactional
public class UsersService extends BaseService<User>{


    public User save(User user){
        generalRepository.getEntityManager().persist(user);
        return user;
    }
}

package com.xiaojiuwo.services;

import com.xiaojiuwo.models.User;
import com.xiaojiuwo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by liuhaibao on 15/12/31.
 */
@Service
@Transactional
public class UsersService extends BaseService<User>{

    @Autowired
    private UserRepository repository;

    public User save(User user){
        return repository.save(user);

    }

    public List<User> findByName(String name){
        User user = new User();
        user.setName(name);
        return myBatisGeneralRepository.getSqlSession().selectList("com.xiaojiuwo.models.mapper.UsersMapper.findUsersByName", user);
    }

    /**
     * warning: this version is bad, do not use it in production
     * it is a version of sql inject verion, try to prevent this when in production
     * @param name
     * @return
     */
    public List<User> findByNameSqlInject(String name, String password){
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        return myBatisGeneralRepository.getSqlSession().selectList("com.xiaojiuwo.models.mapper.UsersMapper.sqlInject", user);
    }
}

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
}

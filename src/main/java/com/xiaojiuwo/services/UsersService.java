package com.xiaojiuwo.services;

import com.xiaojiuwo.models.User;
import com.xiaojiuwo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}

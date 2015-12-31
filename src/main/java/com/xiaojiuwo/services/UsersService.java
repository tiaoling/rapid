package com.xiaojiuwo.services;

import com.xiaojiuwo.dao.UserRepository;
import com.xiaojiuwo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by liuhaibao on 15/12/31.
 */
@Service
@Transactional
public class UsersService {

    @Autowired
    private UserRepository userRepository;

    public Long save(User user){
        return userRepository.save(user);
    }
}

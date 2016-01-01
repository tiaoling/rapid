package com.xiaojiuwo.services;

import com.xiaojiuwo.Application;
import com.xiaojiuwo.repositories.UserRepository;

import com.xiaojiuwo.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by liuhaibao on 15/11/2.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)

//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class UsersServiceTest {

    @Autowired
    private UsersService usersService;


    @Autowired
    UserRepository userRepository;

    @Test
    public void testSave(){
        User user = new User();

        user.setName("ass");
        User user1 = usersService.save(user);
        assertThat("", user1.getId(), greaterThan(0L));

    }

    @Test
    public void testRepoSave(){
        User user = new User();

        user.setName("ass");
        User user1 = userRepository.save(user);
        assertThat("", user1.getId(), greaterThan(0L));

    }
}

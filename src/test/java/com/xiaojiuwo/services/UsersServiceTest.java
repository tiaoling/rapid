package com.xiaojiuwo.services;

import com.xiaojiuwo.Application;
import com.xiaojiuwo.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.junit.Assert.assertEquals;

/**
 * Created by liuhaibao on 15/11/2.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)

public class UsersServiceTest {

    @Autowired
    private UsersService usersService;



    @Test
    public void testSave(){
        User user = new User();

        String name = "assss";
        user.setName(name);
        User user1 = usersService.save(user);
        assertThat("", user1.getId(), greaterThan(0L));
        assertEquals(name, user1.getName());
    }


    @Test
    public void testFindByName(){
        User user = new User();

        String name = "assss";
        user.setName(name);
        User user1 = usersService.save(user);
        List<User> users = usersService.findByName(name);
        assertThat("", users.size(), greaterThan(0));
        assertEquals(name, users.get(0).getName());
    }
}

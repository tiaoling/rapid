package com.xiaojiuwo.services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xiaojiuwo.Application;

/**
 * Created by liuhaibao on 15/11/2.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ImportResource("classpath:config/applicationContext.xml")
@Transactional
public class UsersServiceTest {

    @Autowired
    private UsersService usersService;


    @Test
    public void testSave(){
        /*User user = new User();

        String name = "assss";
        user.setName(name);
        usersService.save(user);
        assertThat("", user.getId(), greaterThan(0L));
        assertEquals(name, user.getName());*/
    }


    @Test
    public void testFindByName(){
        /*User user = new User();

        String name = "assss";
        user.setName(name);
        User user1 = usersService.save(user);
        List<User> users = usersService.findByName(name);
        assertThat("", users.size(), greaterThan(0));
        assertEquals(name, users.get(0).getName());*/
    }
}

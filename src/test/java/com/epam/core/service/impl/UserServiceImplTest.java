package com.epam.core.service.impl;

import com.epam.spring.core.domain.User;
import com.epam.spring.core.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImplTest {
    private static ApplicationContext context;
    private UserServiceImpl userService;
    private List<User> testUsers;

    private static final int SIZE = 10;

    @BeforeClass
    public static void beforeClass() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Before
    public void before() {
        userService = (UserServiceImpl) context.getBean("TestUser");

        User user;
        testUsers = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            user = (User) context.getBean("TestUser");
            user.setEmail("TestUser" + i + "@somemail.com");
            user.setFirstName("TestUser#" + i);
            userService.save(user);
            testUsers.add(user);
        }
    }

    @Test
    public void getUserByEmail() throws Exception {
        for (User user : testUsers) {
            Assert.assertEquals(user, userService.getUserByEmail(user.getEmail()));
        }
    }
}


package com.epam.spring.core.service.impl;

import com.epam.spring.core.domain.User;
import com.epam.spring.core.domain.builder.BuilderFactory;
import com.epam.spring.core.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:context.xml")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    private final User user1 = BuilderFactory.getUserBuilder()
            .buildEmail("fake")
            .buildId(1L)
            .build();
    private final User user2 = BuilderFactory.getUserBuilder()
            .buildEmail("fake2")
            .buildId(2L)
            .build();

    @Test
    public void getUserByEmail() {
        userService.save(user1);
        userService.save(user2);
        assertEquals(user1, userService.getUserByEmail("fake"));
    }

    @Test
    public void getById() {
        userService.save(user1);
        userService.save(user2);
        assertEquals(user1, userService.getById(1L));
    }

    @Test
    public void save() {
        assertEquals(user1,userService.save(user1));
    }

    @Test
    public void remove(){
        userService.save(user1);
        userService.save(user2);
        userService.remove(user1);
        assertEquals(1,userService.getAll().size());
    }

}

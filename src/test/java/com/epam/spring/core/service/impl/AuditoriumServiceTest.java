package com.epam.spring.core.service.impl;

import com.epam.spring.core.service.AuditoriumService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Objects;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:context.xml")
public class AuditoriumServiceTest {

    @Autowired
    private AuditoriumService auditoriumService;

    @Test
    public void getByName(){
        assertEquals("platinum", Objects.requireNonNull(auditoriumService.getByName("platinum")).getName());
    }

    @Test
    public void getAll(){
        assertEquals(3,auditoriumService.getAll().size());
    }
}

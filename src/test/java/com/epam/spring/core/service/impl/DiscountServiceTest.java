package com.epam.spring.core.service.impl;

import com.epam.spring.core.domain.Event;
import com.epam.spring.core.domain.User;
import com.epam.spring.core.domain.builder.BuilderFactory;
import com.epam.spring.core.service.DiscountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.time.LocalDateTime;

import static junit.framework.TestCase.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:context-context.xml")
public class DiscountServiceTest {
    @Autowired
    private DiscountService discountService;

    private final User user= BuilderFactory.getUserBuilder()
            .buildBirthday(LocalDateTime.parse("1985-04-14T10:10:10"))
            .build();

    private final LocalDateTime dateOfEvent=LocalDateTime.parse("2016-04-09T10:10:10");

    private int numberOfTickets=1;

    private final Event event=BuilderFactory.getEventBuilder()
            .buildAirDates(dateOfEvent)
            .build();

    @Test
    public void getDiscountBirthday(){
        assertEquals(5.0,discountService.getDiscount(user,event,dateOfEvent,numberOfTickets));
    }

    @Test
    public void getDiscountNumber(){
        numberOfTickets=15;
        assertEquals(33.0,discountService.getDiscount(user,event,dateOfEvent,numberOfTickets));
    }
}

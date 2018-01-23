package com.epam.spring.core.service.impl;

import com.epam.spring.core.domain.Event;
import com.epam.spring.core.domain.builder.BuilderFactory;
import com.epam.spring.core.service.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:context-context.xml")
public class EventServiceTest {

    @Autowired
    private EventService eventService;

    private final Event event1 = BuilderFactory.getEventBuilder()
            .buildAirDates(LocalDateTime.parse("2016-04-09T10:10:10"))
            .buildName("Fake")
            .buildId(1L)
            .build();

    private final Event event2= BuilderFactory.getEventBuilder()
            .buildAirDates(LocalDateTime.parse("2045-04-09T10:10:10"))
            .buildName("Fake2")
            .buildId(2L)
            .build();

    @Test
    public void save() {
        assertEquals(event1, eventService.save(event1));
    }

    @Test
    public void getByName() {
        eventService.save(event1);
        assertEquals(event1, eventService.getByName(event1.getName()));
    }

    @Test
    public void getById() {
        eventService.save(event1);
        assertNotNull(eventService.getById(event1.getId()));
    }

    @Test
    public void getAll() {
        eventService.save(event2);
        eventService.save(event1);
        assertEquals(2, eventService.getAll().size());
    }

    @Test
    public void remove(){
        eventService.save(event1);
        eventService.save(event2);
        eventService.remove(event1);
        assertEquals(1,eventService.getAll().size());
    }

}

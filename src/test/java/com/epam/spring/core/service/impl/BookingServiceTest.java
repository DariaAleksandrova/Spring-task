package com.epam.spring.core.service.impl;

import com.epam.spring.core.domain.Event;
import com.epam.spring.core.domain.EventRating;
import com.epam.spring.core.domain.Ticket;
import com.epam.spring.core.domain.User;
import com.epam.spring.core.domain.builder.BuilderFactory;
import com.epam.spring.core.service.AuditoriumService;
import com.epam.spring.core.service.BookingService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:context-context.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookingServiceTest {

    @Autowired
    private AuditoriumService auditoriumService;

    @Autowired
    private BookingService bookingService;

    private final Event event = BuilderFactory.getEventBuilder()
            .buildName("Fake")
            .buildBasePrice(500)
            .buildRating(EventRating.HIGH)
            .build();

    private final User user = BuilderFactory.getUserBuilder()
            .buildRegistered(true)
            .buildBirthday(LocalDateTime.parse("1995-04-09T10:10:10"))
            .build();

    private final Ticket ticket1 = BuilderFactory.getTicketBuilder()
            .buildUser(user)
            .buildEvent(event)
            .buildId(1L)
            .buildDate(LocalDateTime.parse("2015-04-09T10:10:10"))
            .build();

    private final Ticket ticket2 = BuilderFactory.getTicketBuilder()
            .buildUser(user)
            .buildEvent(event)
            .buildId(2L)
            .buildDate(LocalDateTime.parse("2015-04-09T10:10:10"))
            .build();

    private final Set<Long> seats = new HashSet<>();

    @Before
    public void init() {
        seats.add(20L);
        event.addAirDateTime(LocalDateTime.parse("2015-04-09T10:10:10"),auditoriumService.getAll().iterator().next());
    }

    @Test
    public void getTicketPrice() {
        assertEquals(1140, bookingService.getTicketsPrice(event, LocalDateTime.parse("2015-04-09T10:10:10"), user, seats), 0);
    }

    @Test
    public void bookTicketsSuccess() {
        bookingService.bookTickets(new HashSet<>(Arrays.asList(ticket1, ticket2)));
        assertEquals(2, bookingService.getPurchasedTicketsForEvent(event, LocalDateTime.parse("2015-04-09T10:10:10")).size());
    }

    @Test
    public void bookTicketsFail() {
        user.setRegistered(false);
        bookingService.bookTickets(new HashSet<>(Arrays.asList(ticket1, ticket2)));
        assertTrue(bookingService.getPurchasedTicketsForEvent(event, LocalDateTime.parse("2015-04-09T10:10:10")).isEmpty());
    }

}

package com.epam.core.service.impl;

import com.epam.spring.core.domain.*;
import com.epam.spring.core.service.impl.BookingServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

public class BookingServiceImplTest {
    private ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    private BookingServiceImpl bookingService = (BookingServiceImpl) ctx.getBean("bookingServiceImpl");
    private User user = (User) ctx.getBean("user");
    private Event event = (Event) ctx.getBean("event");
    private LocalDateTime dateTime = LocalDateTime.of(2017, 3, 5, 12, 30);
    private Set<Long> seats = new HashSet<>();

    @Before
    public void initTest() {
        user.setFirstName("Vasya");
        user.setLastName("Petrov");
        user.setEmail("petrov@gmail.com");
        user.setBirthday(LocalDate.of(2017, 3, 12));

        event = new Event();
        event.setName("Zootopia");
        event.setBasePrice(300);
        event.setRating(EventRating.HIGH);
        Auditorium auditorium = (Auditorium) ctx.getBean("auditorium_second_stage");
        TreeMap<LocalDateTime, Auditorium> auditoriums = new TreeMap<>();
        auditoriums.put(LocalDateTime.of(2017, 3, 5, 12, 30), auditorium);
        event.setAuditoriums(auditoriums);

        seats.add(1L);
        seats.add(3L);
        seats.add(12L);
        seats.add(15L);
        seats.add(19L);
    }

    @Test
    public void getTicketsPriceWithoutDiscount() {
        double price = bookingService.getTicketsPrice(event, dateTime, user, seats);
        assertEquals(2520, price, 0.1);
    }

    @Test
    public void getTicketsPriceWithDiscount() {
        user.setBirthday(LocalDate.of(2017, 3, 8));
        double price = bookingService.getTicketsPrice(event, dateTime, user, seats);
        assertEquals(2394, price, 0.1);
    }

    @Test
    public void getPurchasedTicketsForEvent() {
        Event newEvent = new Event();
        newEvent.setName("Ferdinand");
        Ticket firstTicket = new Ticket(event, dateTime, 3);
        Ticket secondTicket = new Ticket(newEvent, dateTime, 5);
        HashSet<Ticket> tickets = new HashSet<>();
        tickets.add(firstTicket);
        tickets.add(secondTicket);
        bookingService.bookTickets(tickets);
        tickets.remove(secondTicket);
        Set<Ticket> result = bookingService.getPurchasedTicketsForEvent(event, dateTime);
        assertEquals(new HashSet<>(result), tickets);
    }
}

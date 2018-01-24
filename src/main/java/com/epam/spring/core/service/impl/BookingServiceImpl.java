package com.epam.spring.core.service.impl;

import com.epam.spring.core.domain.Event;
import com.epam.spring.core.domain.User;
import com.epam.spring.core.dao.TicketDao;
import com.epam.spring.core.domain.Ticket;
import com.epam.spring.core.service.BookingService;
import com.epam.spring.core.service.DiscountService;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.Set;

public class BookingServiceImpl implements BookingService {

    private DiscountService discountService;
    private TicketDao ticketDao;


    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime,
                                  User user, @Nonnull Set<Long> seats) {
        int countOfTickets = seats.size();
        double discount = discountService.getDiscount(user, event, dateTime, countOfTickets);
        long vipSeatsCount = event.getAuditoriums().get(dateTime).countVipSeats(seats);
        double coefficient = event.getRating().getCoefficient();
        double resultPrice = coefficient * event.getBasePrice() * vipSeatsCount * 2 + coefficient * event.getBasePrice() * (countOfTickets - vipSeatsCount);
        return resultPrice - resultPrice * discount * 0.01;
    }


    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {
        ticketDao.saveAll(tickets);
    }


    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        return ticketDao.getTicketsForDateAndEvent(event, dateTime);
    }

    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public void setDiscountService(DiscountService discountService) {
        this.discountService = discountService;
    }
}

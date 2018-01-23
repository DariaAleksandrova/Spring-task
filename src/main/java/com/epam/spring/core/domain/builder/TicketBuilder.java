package com.epam.spring.core.domain.builder;

import com.epam.spring.core.domain.Event;
import com.epam.spring.core.domain.Ticket;
import com.epam.spring.core.domain.User;

import java.time.LocalDateTime;

/**
 * Created by Дмитрий on 18.04.2016.
 */
public class TicketBuilder {
    Ticket ticket = new Ticket();


    public TicketBuilder buildUser(User user) {
        ticket.setUser(user);
        return this;
    }

    public TicketBuilder buildEvent(Event event) {
        ticket.setEvent(event);
        return this;
    }

    public TicketBuilder buildSeat(Long seat) {
        ticket.setSeat(seat);
        return this;
    }

    public TicketBuilder buildDate(LocalDateTime dateTime) {
        ticket.setDateTime(dateTime);
        return this;
    }

    public TicketBuilder buildId(Long id) {
        ticket.setId(id);
        return this;
    }

    public Ticket build() {
        return ticket;
    }
}

package com.epam.spring.core.dao.impl;

import com.epam.spring.core.dao.TicketDao;
import com.epam.spring.core.domain.Event;
import com.epam.spring.core.domain.Ticket;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TicketDaoImpl implements TicketDao {

    private Map<Long, Ticket> tickets = new HashMap<>();

    public void saveAll(Set<Ticket> tickets) {
        this.tickets.putAll(tickets.stream().filter(t -> t.getUser().isRegistered()).collect(Collectors.toMap(t -> t.getId(), t -> t)));
    }

    public Set<Ticket> getTicketsForDateAndEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        return tickets.values().stream()
                .filter(ticket -> ticket.getDateTime().equals(dateTime))
                .filter(ticket -> ticket.getEvent().equals(event))
                .filter(ticket -> ticket.getUser() != null)
                .collect(Collectors.toSet());
    }

    public void setTickets(Map<Long, Ticket> tickets) {
        this.tickets = tickets;
    }
}

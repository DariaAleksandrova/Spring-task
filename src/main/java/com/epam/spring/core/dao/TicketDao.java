package com.epam.spring.core.dao;

import com.epam.spring.core.domain.Event;
import com.epam.spring.core.domain.Ticket;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.Set;

public interface TicketDao {

    void saveAll(Set<Ticket> tickets);

    Set<Ticket> getTicketsForDateAndEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime);
}

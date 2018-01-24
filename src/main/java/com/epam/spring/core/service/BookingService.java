package com.epam.spring.core.service;

import java.time.LocalDateTime;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.epam.spring.core.domain.Event;
import com.epam.spring.core.domain.User;
import com.epam.spring.core.domain.Ticket;


public interface BookingService {

        double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime,
                               @Nullable User user, @Nonnull Set<Long> seats);


        void bookTickets(@Nonnull Set<Ticket> tickets);


        @Nonnull Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event,
                                                         @Nonnull LocalDateTime dateTime);

}

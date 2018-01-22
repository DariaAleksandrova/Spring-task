package com.epam.spring.core.service.discount;

import com.epam.spring.core.domain.Event;
import com.epam.spring.core.domain.User;

import java.time.LocalDateTime;

public interface Discount {

    byte getDiscount(User user, Event event, LocalDateTime airDateTime, long numberOfTickets);
}

package com.epam.spring.core.service.discount.impl;

import com.epam.spring.core.domain.Event;
import com.epam.spring.core.domain.User;
import com.epam.spring.core.service.discount.Discount;

import java.time.LocalDateTime;

public class Ticket10ThDiscountImpl implements Discount {
    @Override
    public byte getDiscount(User user, Event event, LocalDateTime airDateTime, long numberOfTickets) {
        long x;
        if (user != null) {
            x = (user.getTickets().size() % 10 + numberOfTickets) / 10;
        } else {
            x = numberOfTickets / 10;
        }
        double actualTickets = numberOfTickets - x*0.5;
        return (byte) ((numberOfTickets - actualTickets)*100);
    }
}

package com.epam.spring.core.service.discount.impl;

import com.epam.spring.core.domain.Event;
import com.epam.spring.core.domain.User;
import com.epam.spring.core.service.discount.Discount;

import java.time.LocalDateTime;


public class BirthdayDiscountImpl implements Discount {

    @Override
    public byte getDiscount(User user, Event event, LocalDateTime airDateTime, long numberOfTickets) {
        if (user != null && Math.abs(user.getBirthday().getDayOfYear() - airDateTime.getDayOfYear()) <= 5) {
            return 5;
        }
        return 0;
    }
}

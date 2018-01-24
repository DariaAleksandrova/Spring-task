package com.epam.spring.core.service.discount;

import com.epam.spring.core.domain.Event;
import com.epam.spring.core.domain.User;


import java.time.LocalDateTime;

import static java.lang.Math.abs;


public enum DiscountStrategy {
    BIRTHDAY((byte) 5) {
        public byte discount(User user, Event event, LocalDateTime dateTime, long numberOfTickets) {
            LocalDateTime minus5Days = dateTime.minusDays(5);
            LocalDateTime plus5Days = dateTime.plusDays(5);
            if (minus5Days.getYear() == plus5Days.getYear()) {
                return abs(user.getBirthday().withYear(dateTime.getYear()).getDayOfYear() - dateTime.getDayOfYear()) <= 5 ? this.baseDiscount : 0;
            } else {
                if (plus5Days.getMonth() == user.getBirthday().getMonth() && user.getBirthday().withYear(plus5Days.getYear()).isBefore(plus5Days)) {
                    return this.baseDiscount;
                }
                if (minus5Days.getMonth() == user.getBirthday().getMonth() && user.getBirthday().withYear(minus5Days.getYear()).isAfter(minus5Days)) {
                    return this.baseDiscount;
                }
            }
            return 0;
        }
    },
    NUMBER_OF_TICKETS((byte) 50) {
        public byte discount(User user, Event event, LocalDateTime dateTime, long numberOfTickets) {
            double discountPercent = ((((double) 100) * (numberOfTickets - numberOfTickets % this.ticketsDenominator)) / numberOfTickets / ((double) 100 / this.baseDiscount));
            double normalPercent = ((((double) 100) * (numberOfTickets % this.ticketsDenominator)) / numberOfTickets);
            return numberOfTickets / this.ticketsDenominator > 0 ? (byte) (100 - (discountPercent + normalPercent)) : 0;
        }
    };

    public abstract byte discount(User user, Event event, LocalDateTime dateTime, long numberOfTickets);

    protected int ticketsDenominator = 10;

    protected byte baseDiscount;

    DiscountStrategy(byte baseDiscount) {
        this.baseDiscount = baseDiscount;
    }

    public void setBaseDiscount(byte baseDiscount) {
        this.baseDiscount = baseDiscount;
    }
}

package com.epam.spring.core.domain;

import java.time.LocalDateTime;
import java.util.Objects;


public class Ticket extends DomainObject implements Comparable<Ticket> {

    private User user;

    private Event event;

    private LocalDateTime dateTime;

    private Long seat;

    public Ticket(User user, Event event, LocalDateTime dateTime, Long seat) {
        this.user = user;
        this.event = event;
        this.dateTime = dateTime;
        this.seat = seat;
    }

    public Ticket() {
    }

    public User getUser() {
        return user;
    }

    public Event getEvent() {
        return event;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Long getSeat() {
        return seat;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setSeat(Long seat) {
        this.seat = seat;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public int compareTo(Ticket other) {
        if (other == null) {
            return 1;
        }
        int result = dateTime.compareTo(other.getDateTime());

        if (result == 0) {
            result = event.getName().compareTo(other.getEvent().getName());
        }
        return result;
    }

}


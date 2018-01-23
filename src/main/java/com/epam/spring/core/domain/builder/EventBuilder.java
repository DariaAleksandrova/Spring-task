package com.epam.spring.core.domain.builder;

import com.epam.spring.core.domain.Auditorium;
import com.epam.spring.core.domain.Event;
import com.epam.spring.core.domain.EventRating;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.NavigableSet;
import java.util.TreeMap;
import java.util.TreeSet;


public class EventBuilder {

    private Event event = new Event();

    public EventBuilder buildDateAndAuditorium(LocalDateTime dateTime, Auditorium auditorium) {
        checkForNull(event);
        event.addAirDateTime(dateTime, auditorium);
        return this;
    }

    public EventBuilder buildAirDates(LocalDateTime... localDateTimes) {
        NavigableSet<LocalDateTime> dateTree = new TreeSet<>();
        Arrays.asList(localDateTimes).stream().forEach(localDate -> dateTree.add(localDate));
        event.setAirDates(dateTree);
        return this;
    }

    public EventBuilder buildName(String name) {
        event.setName(name);
        return this;
    }

    public EventBuilder buildRating(EventRating rating) {

        event.setRating(rating);
        return this;
    }

    public EventBuilder buildBasePrice(double basePrice) {
        event.setBasePrice(basePrice);
        return this;
    }

    public Event build() {
        checkForNull(event);
        return event;
    }

    public EventBuilder buildId(Long id) {
        event.setId(id);
        return this;
    }

    private void checkForNull(Event event) {
        if (event.getAuditoriums() == null) {
            event.setAuditoriums(new TreeMap<>());
        }
        if (event.getAirDates() == null) {
            event.setAirDates(new TreeSet<>());
        }
    }
}

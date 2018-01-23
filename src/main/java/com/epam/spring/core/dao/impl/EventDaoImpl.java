package com.epam.spring.core.dao.impl;

import com.epam.spring.core.dao.EventDao;
import com.epam.spring.core.domain.Event;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class EventDaoImpl implements EventDao {

    private Map<Long, Event> events = new HashMap<>();

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        return events.values().stream().filter(event -> event.getName().equals(name)).findAny().orElse(null);
    }

    @Override
    public Event save(@Nonnull Event event) {
        return events.put(event.getId(), event);
    }

    @Override
    public void remove(@Nonnull Event event) {
        events.remove(event.getId());
    }

    @Override
    public Event getById(@Nonnull Long id) {
        return events.get(id);
    }

    @Nonnull
    @Override
    public Set<Event> getForDateRange(@Nonnull LocalDate from, @Nonnull LocalDate to) {
        return events.values().stream().filter(event -> event.airsOnDates(from, to)).collect(Collectors.toSet());
    }

    @Nonnull
    @Override
    public Set<Event> getNextEvents(@Nonnull LocalDateTime to) {
        return events.values().stream().filter(event -> event.airsOnDates(LocalDateTime.now(), to)).collect(Collectors.toSet());
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        return events.values();
    }

    public void setEvents(Map<Long, Event> events) {
        this.events = events;
    }
}

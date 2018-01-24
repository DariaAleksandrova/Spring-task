package com.epam.spring.core.service.impl;

import com.epam.spring.core.dao.EventDao;
import com.epam.spring.core.domain.Event;
import com.epam.spring.core.service.EventService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;


public class EventServiceImpl implements EventService {

    private EventDao eventDao;

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        return eventDao.getByName(name);
    }

    @Override
    public Event save(@Nonnull Event event) {
        return eventDao.save(event);
    }

    @Override
    public void remove(@Nonnull Event event) {
        eventDao.remove(event);
    }

    @Override
    public Event getById(@Nonnull Long id) {
        return eventDao.getById(id);
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        return eventDao.getAll();
    }

    @Nonnull
    @Override
    public Set<Event> getForDateRange(@Nonnull LocalDate from, @Nonnull LocalDate to) {
        return eventDao.getForDateRange(from, to);
    }

    @Nonnull
    @Override
    public Set<Event> getNextEvents(@Nonnull LocalDate to) {
        return eventDao.getNextEvents(to);
    }

    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }
}


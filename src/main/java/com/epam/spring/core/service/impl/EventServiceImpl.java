package com.epam.spring.core.service.impl;

import com.epam.spring.core.domain.Event;
import com.epam.spring.core.dao.EventServiceDao;
import com.epam.spring.core.service.EventService;

import java.util.Collection;

public class EventServiceImpl implements EventService {

    private EventServiceDao eventServiceDao;

    @Override
    public Event getByName(String name) {
        return eventServiceDao.getByName(name);
    }

    @Override
    public Event save(Event object) {
        return eventServiceDao.save(object);
    }

    @Override
    public void remove(Event object) {
        eventServiceDao.remove(object);
    }

    @Override
    public Event getById(Long id) {
        return eventServiceDao.getById(id);
    }

    @Override
    public Collection<Event> getAll() {
        return eventServiceDao.getAll();
    }

    public void setEventServiceDao(EventServiceDao eventServiceDao) {
        this.eventServiceDao = eventServiceDao;
    }
}

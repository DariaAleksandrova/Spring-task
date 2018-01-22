package com.epam.spring.core.dao;

import com.epam.spring.core.domain.Event;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EventServiceDao {

    private static Map<Long, Event> events = new HashMap<Long, Event>();

    public Event save(Event object) {
        return events.put(object.getId(), object);
    }

    public void remove(Event object) {
        events.remove(object.getId());
    }

    public Event getById(Long id) {
        return events.get(id);
    }

    public Collection<Event> getAll() {
        return events.values();
    }

    public Event getByName(String name) {
        List<Event> eventsByName = events.values().stream().
                filter(event -> event.getName().equals(name)).
                collect(Collectors.toList());
        return eventsByName.isEmpty() ? null : eventsByName.get(0);
    }
}

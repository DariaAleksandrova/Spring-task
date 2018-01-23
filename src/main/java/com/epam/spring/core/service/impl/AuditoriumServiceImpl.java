package com.epam.spring.core.service.impl;

import com.epam.spring.core.domain.Auditorium;
import com.epam.spring.core.domain.Event;
import com.epam.spring.core.service.AuditoriumService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Set;

public class AuditoriumServiceImpl implements AuditoriumService{

    private Set<Auditorium> auditoriums;

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return auditoriums;
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        return auditoriums.stream().filter(auditorium ->
                auditorium.getName().equals(name)).findFirst().orElse(null);
    }

    public Set<Auditorium> getAuditoriums(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        return auditoriums;
    }

    public void setAuditoriums(Set<Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
    }

    @Override
    public Auditorium getAuditoriumByEventAndDate(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        return event.getAuditoriums().get(dateTime);
    }
}

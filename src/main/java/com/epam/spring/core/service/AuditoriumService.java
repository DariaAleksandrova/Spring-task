package com.epam.spring.core.service;

import java.time.LocalDateTime;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.epam.spring.core.domain.Auditorium;
import com.epam.spring.core.domain.Event;


public interface AuditoriumService {

    @Nonnull Set<Auditorium> getAll();

    @Nullable Auditorium getByName(@Nonnull String name);

    @Nullable
    Auditorium getAuditoriumByEventAndDate(@Nonnull Event event, @Nonnull LocalDateTime dateTime);
}

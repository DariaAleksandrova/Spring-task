package com.epam.spring.core.dao;

import com.epam.spring.core.domain.Event;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

public interface EventDao {

    @Nullable
    Event getByName(@Nonnull String name);

    Event save(@Nonnull Event object);

    void remove(@Nonnull Event object);

    Event getById(@Nonnull Long id);

    @Nonnull
    Set<Event> getNextEvents(@Nonnull LocalDate to);

    @Nonnull
    Collection<Event> getAll();

    @Nonnull
    Set<Event> getForDateRange(@Nonnull LocalDate from,
                               @Nonnull LocalDate to);

}

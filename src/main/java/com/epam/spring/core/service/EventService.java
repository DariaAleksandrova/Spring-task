package com.epam.spring.core.service;

import com.epam.spring.core.domain.Event;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.util.Set;

public interface EventService extends AbstractDomainObjectService<Event> {

    @Nullable
    Event getByName(@Nonnull String name);

    @Nonnull
    Set<Event> getForDateRange(@Nonnull LocalDate from,
                               @Nonnull LocalDate to);

    @Nonnull
    Set<Event> getNextEvents(@Nonnull LocalDate to);

}

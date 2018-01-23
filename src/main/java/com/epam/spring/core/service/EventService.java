package com.epam.spring.core.service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.epam.spring.core.domain.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;


public interface EventService extends AbstractDomainObjectService<Event> {

    @Nullable Event getByName(@Nonnull String name);




}

package com.epam.spring.core.service;

import java.util.Collection;

import javax.annotation.Nonnull;

import com.epam.spring.core.domain.DomainObject;


public interface AbstractDomainObjectService<T extends DomainObject> {


    T save(@Nonnull T object);


    void remove(@Nonnull T object);


    T getById(@Nonnull Long id);


    @Nonnull Collection<T> getAll();
}

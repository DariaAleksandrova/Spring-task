package com.epam.spring.core.service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.epam.spring.core.domain.Ticket;
import com.epam.spring.core.domain.User;


public interface UserService extends AbstractDomainObjectService<User> {

  @Nullable User getUserByEmail(@Nonnull String email);

}


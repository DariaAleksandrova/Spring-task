package com.epam.spring.core.dao;

import com.epam.spring.core.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;


public interface UserDao {

    User save(@Nonnull User object);

    void remove(@Nonnull User object);

    User getById(@Nonnull Long id);

    @Nonnull
    Collection<User> getAll();

    @Nullable
    User getUserByEmail(@Nonnull String email);
}

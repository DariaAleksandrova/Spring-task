package com.epam.spring.core.dao.impl;

import com.epam.spring.core.dao.UserDao;
import com.epam.spring.core.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class UserDaoImpl implements UserDao {

    private Map<Long, User> users = new HashMap<>();

    @Override
    public User save(@Nonnull User user) {
        return users.put(user.getId(), user);
    }

    @Override
    public void remove(@Nonnull User user) {
        users.remove(user.getId());
    }

    @Override
    public User getById(@Nonnull Long id) {
        return users.get(id);
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        return users.values();
    }

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        return users.values().stream().filter(user -> user.getEmail().equals(email)).findAny().orElse(null);
    }

    public void setUsers(Map<Long, User> users) {
        this.users = users;
    }
}

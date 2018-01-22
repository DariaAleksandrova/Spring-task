package com.epam.spring.core.dao;

import com.epam.spring.core.domain.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserDao {

    private static Map<Long, User> users = new HashMap<Long, User>();

    public User save(User object) {
        return users.put(object.getId(), object);
    }

    public void remove(User object) {
        users.remove(object.getId());
    }

    public User getById(Long id) {
        return users.get(id);
    }

    public Collection<User> getAll() {
        return users.values();
    }

    public User getUserByEmail(String email) {
        List<User> usersByEmail = users.values().stream().
                filter(user -> user.getEmail().equals(email)).
                collect(Collectors.toList());
        return usersByEmail.isEmpty() ? null : usersByEmail.get(0);
    }
}

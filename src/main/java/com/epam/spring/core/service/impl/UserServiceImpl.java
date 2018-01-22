package com.epam.spring.core.service.impl;

import com.epam.spring.core.domain.User;
import com.epam.spring.core.dao.UserDao;
import com.epam.spring.core.service.UserService;

import java.util.Collection;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public User save(User object) {
        return userDao.save(object);
    }

    @Override
    public void remove(User object) {
        userDao.remove(object);

    }

    @Override
    public User getById( Long id) {
        return userDao.getById(id);
    }

    @Override
    public Collection<User> getAll() {
        return userDao.getAll();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}

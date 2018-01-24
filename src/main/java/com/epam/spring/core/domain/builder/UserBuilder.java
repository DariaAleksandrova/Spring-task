package com.epam.spring.core.domain.builder;

import com.epam.spring.core.domain.Ticket;
import com.epam.spring.core.domain.User;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.NavigableSet;
import java.util.TreeSet;


public class UserBuilder {

    private User user = new User();

    public User build() {
        return user;
    }

    public UserBuilder buildTickets(Ticket... tickets) {
        NavigableSet<Ticket> ticketsTree = new TreeSet<>(Arrays.asList(tickets));
        user.setTickets(ticketsTree);
        return this;
    }

    public UserBuilder buildEmail(String email) {
        user.setEmail(email);
        return this;
    }

    public UserBuilder buildId(Long id) {
        user.setId(id);
        return this;
    }

    public UserBuilder buildBirthday(LocalDateTime localDateTime) {
        user.setBirthday(localDateTime);
        return this;
    }

    public UserBuilder buildRegistered(boolean isReg) {
        user.setRegistered(isReg);
        return this;
    }
}

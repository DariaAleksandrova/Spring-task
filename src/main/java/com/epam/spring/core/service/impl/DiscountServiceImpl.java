package com.epam.spring.core.service.impl;

import com.epam.spring.core.domain.Event;
import com.epam.spring.core.domain.User;
import com.epam.spring.core.service.DiscountService;
import com.epam.spring.core.service.UserService;
import com.epam.spring.core.service.discount.Discount;

import java.time.LocalDateTime;
import java.util.List;

public class DiscountServiceImpl implements DiscountService {

    private UserService userService;
    private List<Discount> discountList;

    public double getDiscount(User user, Event event, LocalDateTime airDateTime, long numberOfTickets) {
        User userFromDB = userService.getById(user.getId());
        User userToCountDiscount = checkIfRegisteredUser(userFromDB, user);
        double discountCurrent = 0;
        for (Discount discount : discountList) {
            double obtainedDiscount = discount.getDiscount(userToCountDiscount, event, airDateTime, numberOfTickets);
                discountCurrent = obtainedDiscount;
            }
        return discountCurrent;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    public void setDiscounts(List<Discount> discounts) {
        this.discountList = discounts;
    }

    private User checkIfRegisteredUser(User userFromDB, User passeUser) {
        if (userFromDB != null) {
            userFromDB.setRegistered(true);
            return userFromDB;
        }
        return passeUser;
    }
}

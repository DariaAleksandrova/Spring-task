package com.epam.spring.core.service.impl;

import com.epam.spring.core.domain.Event;
import com.epam.spring.core.domain.User;
import com.epam.spring.core.service.DiscountService;
import com.epam.spring.core.service.UserService;
import com.epam.spring.core.service.discount.DiscountStrategy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.List;

public class DiscountServiceImpl implements DiscountService {

    private List<DiscountStrategy> strategies;

    public List<DiscountStrategy> getStrategies() {
        return strategies;
    }

    public void setStrategies(List<DiscountStrategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public double getDiscount(@Nullable User user, @Nonnull Event event,
                              @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        return (byte) strategies.stream()
                .mapToInt(strategy -> strategy.discount(user, event, airDateTime, numberOfTickets))
                .max().getAsInt();
    }
}

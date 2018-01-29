package com.epam.spring.core.aop;

import com.epam.spring.core.domain.User;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class DiscountAspect {

    private Map<Byte, Map<User, Long>> discountForEachUserCounter = new HashMap<>();
    private Map<Byte, Long> discountTotalCounter = new HashMap<>();

    public Map<Byte, Map<User, Long>> getDiscountForEachUserCounter() {
        return discountForEachUserCounter;
    }
    public void setDiscountForEachUserCounter(Map<Byte, Map<User, Long>> discountForEachUserCounter) {
        this.discountForEachUserCounter = discountForEachUserCounter;
    }

    public Map<Byte, Long> getDiscountTotalCounter() {
        return discountTotalCounter;
    }
    public void setDiscountTotalCounter(Map<Byte, Long> discountTotalCounter) {
        this.discountTotalCounter = discountTotalCounter;
    }

    @Pointcut("execution(* com.epam.spring.core.service.DiscountService.getDiscount(..))")
    private void countDiscountForEachUser() {
    }

    @AfterReturning(pointcut = "countDiscountForEachUser", returning = "discount")
    public void countDiscountForEachUser(Byte discount, User user) {
        if (!discountForEachUserCounter.containsKey(discount)) {
            Map<User, Long> countForUser = new HashMap<>();
            countForUser.put(user, 1L);
            discountForEachUserCounter.put(discount, countForUser);
            return;
        }
        if (!discountForEachUserCounter.get(discount).containsKey(user)) {
            discountForEachUserCounter.get(discount).put(user, 1L);
            return;
        }
        long counter = discountForEachUserCounter.get(discount).get(user);
        counter++;
        discountForEachUserCounter.get(discount).put(user, counter);
    }

    @Pointcut("execution(* com.epam.spring.core.service.DiscountService.getDiscount(..))")
    private void countTotalDiscount() {
    }

    @AfterReturning(pointcut = "countTotalDiscount", returning = "discount")
    public void countTotalDiscount(Byte discount) {
        if (discountTotalCounter.containsKey(discount)) {
            long countTotal = discountTotalCounter.get(discount);
            countTotal++;
            discountTotalCounter.put(discount, countTotal);
        } else {
            discountTotalCounter.put(discount, 1L);
        }
    }

}

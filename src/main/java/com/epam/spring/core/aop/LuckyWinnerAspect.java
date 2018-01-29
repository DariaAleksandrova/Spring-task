package com.epam.spring.core.aop;

import com.epam.spring.core.domain.Event;
import com.epam.spring.core.domain.Ticket;
import com.epam.spring.core.domain.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Aspect
@Component
public class LuckyWinnerAspect {

    private List<Ticket> luckyTickets = new ArrayList<>();

    public List<Ticket> getLuckyTickets() {
        return luckyTickets;
    }
    public void setLuckyTickets(List<Ticket> luckyTickets) {
        this.luckyTickets = luckyTickets;
    }

//   TODO: разделить?
//    @Pointcut("execution(* com.epam.spring.core.service.BookingService.getTicketsPrice(..))")
//    private void getTicketPrice() {
//    }

    @Around("execution(* com.epam.spring.core.service.BookingService.getTicketsPrice(..))")

    //todo: возможно можно проще, попробовать
    public double getTicketPrice(ProceedingJoinPoint joinPoint, Event event, LocalDateTime date, User user, Set<Long> seats) {
        try {
            return luckyTickets.stream().filter(ticket -> {
                if (!ticket.getDateTime().equals(date) || !ticket.getEvent().equals(event) || !ticket.getUser().equals(user)) {
                    return false;
                }
                return true;
            }).count() > 0 ? 0 : (Double) joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return 0;
    }


//    @Pointcut("execution(void com.epam.spring.core.service.BookingService.bookTickets(..))")
//    private void addLuckyTicket() {
//    }

    @Before("execution(void com.epam.spring.core.service.BookingService.bookTickets(..))")
    public void addLuckyTicket(Set<Ticket> tickets) {
        Random random = new Random();
        tickets.forEach(ticket -> {
            if (random.nextInt(10000) == 42) {
                luckyTickets.add(ticket);
            }
        });
    }

}

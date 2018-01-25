package com.epam.spring.core.aop;


import com.epam.spring.core.domain.Event;
import com.epam.spring.core.domain.Ticket;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Aspect
public class CounterAspect {
    private Map<String, Long> accessByNameCounter = new HashMap<String, Long>();
    private Map<String, Long> requestEventPriceCounter = new HashMap<String, Long>();
    private Map<String, Long> bookTicketEventCounter = new HashMap<String, Long>();


    public Map<String, Long> getAccessByNameCounter() {
        return accessByNameCounter;
    }

    public void setAccessByNameCounter(Map<String, Long> accessByNameCounter) {
        this.accessByNameCounter = accessByNameCounter;
    }

    public Map<String, Long> getRequestEventPriceCounter() {
        return requestEventPriceCounter;
    }

    public void setRequestEventPriceCounter(Map<String, Long> requestEventPriceCounter) {
        this.requestEventPriceCounter = requestEventPriceCounter;
    }

    public Map<String, Long> bookTicketEventCounter() {
        return bookTicketEventCounter;
    }

    public void bookTicketEventCounter(Map<String, Long> bookTicketEventCounter) {
        this.bookTicketEventCounter = bookTicketEventCounter;
    }

    @Pointcut("execution(* com.epam.spring.core.service.impl.EventServiceImpl.getByName(..))")
        private void countAccessByName() {
        }

    @AfterReturning(pointcut = "countAccessByName", returning = "event")
    private void countGetAccessByName(String name, Event event) {
        if (accessByNameCounter.containsKey(name)) {
            long counter = accessByNameCounter.get(name);
            accessByNameCounter.put(name, ++counter);
            return;
        }
        if (event != null) {
            accessByNameCounter.put(name, 1L);
        }
    }

    @Pointcut("execution(* com.epam.spring.core.service.impl.BookingServiceImpl.getTicketsPrice(..))")
    private void getEventPrice() {
    }

    @Before("getEventPrice()")
    public void countGetEventPrice(Event event) {
        if ( requestEventPriceCounter.containsKey(event.getName())) {
            long counter =  requestEventPriceCounter.get(event.getName());
            requestEventPriceCounter.put(event.getName(), ++counter);
        } else {
            requestEventPriceCounter.put(event.getName(), 1L);
        }
    }

    @Pointcut("execution(* com.epam.spring.core.dao.TicketDao.saveAll(..))")
    private void bookTicketEvent(){
    }

    @Before("bookTicketEvent")
    public void countBookedTickets(Set<Ticket> tickets) {
        tickets.forEach(ticket -> {
            if (bookTicketEventCounter.containsKey(ticket.getEvent().getName())) {
                long counter = bookTicketEventCounter.get(ticket.getEvent().getName());
                bookTicketEventCounter.put(ticket.getEvent().getName(), ++counter);
            } else {
                bookTicketEventCounter.put(ticket.getEvent().getName(), 1L);
            }
        });
    }


}

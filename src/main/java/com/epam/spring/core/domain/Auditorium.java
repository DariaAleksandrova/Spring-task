package com.epam.spring.core.domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Auditorium {

    private String name;
    private long numberOfSeats;
    private NavigableSet<Ticket> tickets = new TreeSet<>();
    private Set<Long> vipSeats = Collections.emptySet();

    public Auditorium() {
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void remove(Ticket ticket) {
        tickets.remove(ticket);
    }

    public NavigableSet<Ticket> getTickets() {
        return tickets;
    }

    public long countVipSeats(Collection<Long> seats) {
        return seats.stream().filter(seat -> vipSeats.contains(seat)).count();
    }

    public boolean isSeatVip(Long id) {
        return vipSeats.contains(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(long numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Set<Long> getAllSeats() {
        return LongStream.range(1, numberOfSeats + 1).boxed().collect(Collectors.toSet());
    }

    public Set<Long> getVipSeats() {
        return vipSeats;
    }

    public void setVipSeats(Set<Long> vipSeats) {
        this.vipSeats = vipSeats;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Auditorium other = (Auditorium) obj;
        if (name == null) {
            return other.name == null;
        }  return name.equals(other.name);
    }


}

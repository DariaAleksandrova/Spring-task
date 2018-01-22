package com.epam.spring.core.domain;

public class Seat extends DomainObject {

    private Integer number;
    private TypeSeat type;

    public Seat(Integer number) {
        this.number = number;
        type = TypeSeat.USUAL;
    }

    public Seat() {
        type = TypeSeat.USUAL;
    }

    public TypeSeat getType() {
        return type;
    }

    public void setType(TypeSeat type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }

        Seat seat = (Seat) o;

        if (number != null ? !number.equals(seat.number) : seat.number != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return number != null ? number.hashCode() : 0;
    }
}

package com.epam.spring.core.domain.builder;


public class BuilderFactory {

    public static TicketBuilder getTicketBuilder() {
        return new TicketBuilder();
    }

    public static EventBuilder getEventBuilder() {
        return new EventBuilder();
    }

    public static UserBuilder getUserBuilder() {
        return new UserBuilder();
    }

    private BuilderFactory(){}
}

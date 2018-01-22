package com.epam.spring.core.domain;


public enum EventRating {

    LOW() {
        public double getCoefficient() {
            return 0.8;
        }
    },

    MID() {
        public double getCoefficient() {
            return 1;
        }
    },

    HIGH() {
        public double getCoefficient() {
            return 1.2;
        }
    };

    public abstract double getCoefficient();
}

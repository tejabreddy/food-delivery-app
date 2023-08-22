package com.hashedin.fooddelivery.orderservice.entity;

public enum OrderStatus {

    PLACED("Placed"),
    ACCEPTED("Accepted"),
    PROCESSING("Processing"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

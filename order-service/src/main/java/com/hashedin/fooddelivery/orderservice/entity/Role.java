package com.hashedin.fooddelivery.orderservice.entity;

public enum Role {
    CUSTOMER("Customer"),
    RESTAURANT_STAFF("Restaurant Staff"),
    ADMIN("Admin");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

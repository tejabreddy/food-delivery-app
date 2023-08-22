package com.hashedin.fooddelivery.orderservice.entity;

public enum Category {

    BEVERAGE("Beverage"),
    SALAD("Salad"),
    SOUP("Soup"),
    APPETIZER("Appetiser"),
    DESSERT("Dessert"),
    MAINCOURSE("Main Course");

    private final String value;

    Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

package com.example.communals.entity.enums;

public enum Urgency {
    URGENT("Срочный заказ"),
    NOTURGENT("Несрочный заказ");

    final String description;

    public String getDescription() {
        return description;
    }

    Urgency(String description) {
        this.description = description;
    }
}

package com.example.communals.entity.enums;

public enum OrderStatus {
    OPEN("Открыт"),
    INPROGRESS("В процессе выполнения"),
    CLOSED("Закрыт"),
    CANCELED("Отменен");

    final String description;

    public String getDescription() {
        return description;
    }

    OrderStatus(String description) {
        this.description = description;
    }
}

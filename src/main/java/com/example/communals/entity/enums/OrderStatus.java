package com.example.communals.entity.enums;

public enum OrderStatus {
    OPEN("Открыт"),
    INPROGRESS("В процессе выполнения"),
    CLOSED("Закрыт");

    final String description;

    public String getDescription() {
        return description;
    }

    OrderStatus(String description) {
        this.description = description;
    }
}

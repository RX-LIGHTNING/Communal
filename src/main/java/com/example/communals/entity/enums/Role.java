package com.example.communals.entity.enums;

public enum Role {
    USER("Роль для обычных пользователей"),
    ADMIN("Роль для администраторов системы"),
    EMPLOYEE("Роль для рядовых рабочих");

    final String description;

    public String getDescription() {
        return description;
    }

    Role(String description) {
        this.description = description;
    }
}
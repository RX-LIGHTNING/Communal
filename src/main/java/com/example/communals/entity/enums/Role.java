package com.example.communals.entity.enums;

public enum Role {
    USER("Роль для обычных пользователей"),
    ADMIN("Роль для администраторов системы"),
    EMPLOYEE("Роль для рядовых рабочих"),
    ORDERACCESS("Роль для доступа к спискам заказов"),
    ACTIONACCESS("Роль для доступа к спискам услуг");

    final String description;

    public String getDescription() {
        return description;
    }

    Role(String description) {
        this.description = description;
    }
}
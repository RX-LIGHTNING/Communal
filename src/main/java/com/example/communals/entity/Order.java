package com.example.communals.entity;

import com.example.communals.entity.enums.OrderStatus;
import com.example.communals.entity.enums.Urgency;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
@Data
@RequiredArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    @OneToOne
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    private Action action;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdate = new Date();

    @OneToOne(fetch = FetchType.LAZY)
    private User attachedEmployee = null;
    private String description;

    @Enumerated(EnumType.ORDINAL)
    private Urgency urgency = Urgency.NOTURGENT;

    @Enumerated(EnumType.ORDINAL)
    private OrderStatus orderStatus = OrderStatus.OPEN;

    public Order(Action action, User user) {
        this.setAction(action);
        this.setUser(user);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", action=" + action +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", urgency=" + urgency +
                ", orderStatus=" + orderStatus +
                '}';
    }
}

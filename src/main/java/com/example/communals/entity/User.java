package com.example.communals.entity;

import com.example.communals.entity.enums.Role;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
    private String phone;
    private String adress;
    private String username;
    private String firstname;
    private String surname;
    private String passwordHash;
    private boolean active = true;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    public List<Order> orderList;
}

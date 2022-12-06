package com.example.communals.repo;

import com.example.communals.entity.Order;
import com.example.communals.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepo extends CrudRepository<Order,Long> {
    List<Order> findAllByUser(User user);
    List<Order> findAllByAttachedEmployee(User user);
}

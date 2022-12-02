package com.example.communals.service;

import com.example.communals.entity.Order;
import com.example.communals.repo.OrderRepo;
import com.example.communals.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepo orderRepo;
    private final UserRepo userRepo;

    public Order save(Order order){
        order.setUser(userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        return orderRepo.save(order);
    }

    public Iterable<Order> getAll(){
        return orderRepo.findAll();
    }

    public Order getById(Long id){
        return orderRepo.findById(id).get();
    }
}

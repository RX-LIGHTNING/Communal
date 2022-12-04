package com.example.communals.service;

import com.example.communals.entity.Order;
import com.example.communals.repo.OrderRepo;
import com.example.communals.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepo orderRepo;
    private final UserRepo userRepo;

    private final UserService userService;

    public Order save(Order order) {
        order.setUser(userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        return orderRepo.save(order);
    }

    public Iterable<Order> getAll() {
        return orderRepo.findAll();
    }

    public Order getById(Long id) {
        return orderRepo.findById(id).get();
    }

    public List<Order> getAllByUsername(String username) {
        return orderRepo.findAllByUser(userService.findByUsername(username));
    }

    public void delete(Long id){
        orderRepo.deleteById(id);
    }
}

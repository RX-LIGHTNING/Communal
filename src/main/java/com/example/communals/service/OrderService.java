package com.example.communals.service;

import com.example.communals.entity.Order;
import com.example.communals.entity.enums.OrderStatus;
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
    public Order edit(Order order) {
        order.setUser(orderRepo.findById(order.getId()).get().getUser());
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

    public void delete(Long id) {
        orderRepo.deleteById(id);
    }

    public void cancelOrder(Long id, String username) {
        Order order = orderRepo.findById(id).get();
        if (order.getUser().getUsername().equals(username)){
            order.setOrderStatus(OrderStatus.CANCELED);
            orderRepo.save(order);
        }
    }
    public List<Order> getAllByAttachedEmployee(String username) {
        return orderRepo.findAllByAttachedEmployee(userService.findByUsername(username));
    }
    public void changeOrderStatus(OrderStatus orderStatus, Long id){
        Order order = orderRepo.findById(id).get();
        order.setOrderStatus(orderStatus);
        orderRepo.save(order);
    }
}

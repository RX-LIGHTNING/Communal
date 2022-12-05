package com.example.communals.controller;

import com.example.communals.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/orders")
    public String getUsersOrders(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("orders", orderService.getAllByUsername(auth.getName()));
        return "usersOrders";
    }
    @GetMapping("/orders/cancel")
    public String cancelOrders(Model model, @RequestParam(name="id", required = true)long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        orderService.cancelOrder(id, auth.getName());
        return "redirect:/orders";
    }
}

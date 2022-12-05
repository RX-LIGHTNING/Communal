package com.example.communals.controller;

import com.example.communals.entity.Order;
import com.example.communals.entity.User;
import com.example.communals.entity.enums.OrderStatus;
import com.example.communals.entity.enums.Urgency;
import com.example.communals.service.ActionService;
import com.example.communals.service.OrderService;
import com.example.communals.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ActionController {

    private final ActionService actionService;
    private final UserService userService;
    private final OrderService orderService;

    @GetMapping("/action")
    public String getServices(Model model){
        model.addAttribute("actions", actionService.getAll());
        return "action";
    }
    @GetMapping("/action/filter")
    public String getServicesByFilter(Model model, @RequestParam(name="search", required = true)String search){
        model.addAttribute("actions", actionService.getAllByFilter(search));
        return "action";
    }


    @GetMapping("/action/order")
    public String getOrderWindow(Model model, @RequestParam(name="id", required = true)long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Order order = new Order(actionService.getById(id),userService.findByUsername(auth.getName()));
        model.addAttribute("Order",order);
        model.addAttribute("orderUrgency", Urgency.values());
        return "order";
    }

    @PostMapping("/action/order/accept")
    public String acceptOrder(@ModelAttribute("Order") Order order){
        orderService.save(order);
        return "redirect:/";
    }
}

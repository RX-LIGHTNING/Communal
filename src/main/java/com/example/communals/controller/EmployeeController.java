package com.example.communals.controller;

import com.example.communals.entity.enums.OrderStatus;
import com.example.communals.service.OrderService;
import com.example.communals.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class EmployeeController {
    private final OrderService orderService;
    private final UserService userService;

    @GetMapping("/employee")
    public String getAdminPage(Model model) {
        return "/employee/panel";
    }

    @GetMapping("/employee/orders")
    public String getEmployeeOrders(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("orders", orderService.getAllByAttachedEmployee(auth.getName()));
        model.addAttribute("status", OrderStatus.values());
        return "/employee/orderCRUD/orders";
    }

    @PostMapping("/employee/orders/edit")
    public String editEmployeeOrders(Model model, @RequestParam(name = "status") OrderStatus status,
                                     @RequestParam(name = "id") Long id) {
        orderService.changeOrderStatus(status,id);
        return "redirect:/employee/orders";
    }
    @GetMapping("/employee/user/view")
    public String getAdminPage(Model model, @RequestParam(name = "id") Long id) {
        model.addAttribute("user", userService.getById(id));
        return "/employee/orderCRUD/user";
    }
}

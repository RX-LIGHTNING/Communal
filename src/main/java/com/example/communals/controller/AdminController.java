package com.example.communals.controller;

import com.example.communals.entity.Action;
import com.example.communals.entity.Order;
import com.example.communals.entity.User;
import com.example.communals.entity.enums.OrderStatus;
import com.example.communals.entity.enums.Role;
import com.example.communals.entity.enums.Urgency;
import com.example.communals.service.ActionService;
import com.example.communals.service.OrderService;
import com.example.communals.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final ActionService actionService;
    private final OrderService orderService;

    private final UserService userService;

    @GetMapping("/admin")
    public String getAdminPage(Model model){
        return "/admin/panel";
    }

    @GetMapping("/admin/actions")
    public String getServices(Model model){
        model.addAttribute("actions", actionService.getAll());
        return "/admin/actionCRUD/actions";
    }
    @GetMapping("/admin/actions/edit")
    public String editService(Model model, @RequestParam(name="id", required = true)long id){

        model.addAttribute("Action", actionService.getById(id));
        return "/admin/actionCRUD/edit";
    }
    @GetMapping("/admin/actions/delete")
    public String deleteService(Model model, @RequestParam(name="id", required = true)long id){
        return "redirect:/admin/actions";
    }
    @GetMapping("/admin/actions/add")
    public String addService(Model model){
        model.addAttribute("action", new Action());
        return "/admin/actionCRUD/add";
    }
    @PostMapping("/admin/actions/change/accept")
    public String editServiceAccept(Model model, @ModelAttribute("action") Action action){
        model.addAttribute("action", action);
        actionService.save(action);
        return "redirect:/admin/actions";
    }
    @GetMapping("/admin/orders")
    public String getOrders(Model model){
        model.addAttribute("orders", orderService.getAll());
        return "/admin/orderCRUD/orders";
    }
    @GetMapping("/admin/orders/edit")
    public String getOrders(Model model, @RequestParam(name="id", required = true)long id){
        Order order = orderService.getById(id);
        model.addAttribute("Order", order);
        model.addAttribute("orderUrgency", Urgency.values());
        model.addAttribute("status", OrderStatus.values());
        model.addAttribute("employees", userService.findAllByRole(Role.EMPLOYEE));
        return "/admin/orderCRUD/edit";
    }
    @GetMapping("/admin/orders/delete")
    public String deleteOrder(Model model, @RequestParam(name="id", required = true)long id){
        orderService.delete(id);
        return "redirect:/admin/orders";
    }
    @PostMapping("/admin/orders/accept")
    public String changeOrders(Model model, @ModelAttribute("order") Order order){
        orderService.save(order);
        return "redirect:/admin/orders";
    }


    @GetMapping("/admin/users")
    public String getUser(Model model){
        model.addAttribute("users", userService.getAll());
        return "/admin/userCRUD/users";
    }
    @GetMapping("/admin/users/edit")
    public String editUser(Model model, @RequestParam(name="id", required = true)long id){
        User user = userService.getById(id);
        model.addAttribute("User", user);
        model.addAttribute("Roles", Role.values());
        return "/admin/userCRUD/edit";
    }
    @PostMapping("/admin/users/edit/accept")
    public String editUserAccept(Model model, @ModelAttribute("User") User user, @RequestParam(value = "selected", required = false)Role[] selected){
        userService.edit(user, selected);
        return "redirect:/admin/users";
    }
    @GetMapping("/admin/users/delete")
    public String deleteUser(Model model, @RequestParam(name="id", required = true)long id){
        userService.delete(id);
        return "redirect:/admin/users";
    }
}

package com.example.communals.controller;


import com.example.communals.service.ActionService;
import com.example.communals.service.OrderService;
import com.example.communals.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

    private final ActionService actionService;
    private final OrderService orderService;
    private final UserService userService;

    @GetMapping("/employee")
    public String getEmployeePage(Model model){
        return "/employee/panel";
    }
}

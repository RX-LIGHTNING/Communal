package com.example.communals.controller;

import com.example.communals.entity.User;
import com.example.communals.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/login")
    public String getLoginPage(Model model){
        return "login";
    }
    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("User",new User());
        return "registration";
    }
    @PostMapping("/register/accept")
    public String userRegister(@ModelAttribute("User") User user){
        userService.registration(user);
        return "redirect:/";
    }
}

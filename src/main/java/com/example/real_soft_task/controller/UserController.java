package com.example.real_soft_task.controller;

import com.example.real_soft_task.dto.LoginDto;
import com.example.real_soft_task.dto.RegisterDto;
import com.example.real_soft_task.dto.UserDto;
import com.example.real_soft_task.model.User;
import com.example.real_soft_task.repository_service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/first")
    public String first() {
        return "adminPage";
    }

    @GetMapping("/second")
    public String second() {
        return "userPage";
    }

    private final UserService userService;
    static User currentUser = null;

    @PostMapping("/login")
    public String login(@ModelAttribute LoginDto dto) {
        List<User> all = userService.findAll();
        for (User user : all) {
            if (user.getEmail().equals(dto.getEmail()) && user.getPassword().equals(dto.getPassword())) {
                currentUser = user;
                if (user.getRole().equals("ADMIN")) {
                    return "adminPage";
                } else {
                    return "userPage";
                }
            }
        }
        return "index";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterDto dto) {
        boolean hasUser = false;
        List<User> all = userService.findAll();
        for (User user : all) {
            if (user.getEmail().equals(dto.getEmail())) {
                hasUser = true;
                break;
            }
        }
        if (!hasUser && dto.getConfirmPassword().equals(dto.getPassword())) {
            userService.addUser(new UserDto(dto.getUserName(), dto.getEmail(), dto.getPassword()));
        }
        return "index";
    }
}

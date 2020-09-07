package com.example.demo.controllers;


import com.example.demo.models.User;
import com.example.demo.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.findUser());
        return "index";
    }
    @GetMapping("/sign-up")
    public String signUpForm(User user) {
        return "sign-up";
    }


    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userService.findUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("user", user);
        return "edituser";
    }
    @PostMapping("update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            user.id=id;
            return "edituser";
        }

        userService.saveUser(user);
        return "redirect:/";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "sign-up";
        }
        userService.saveUser(user);
        return "redirect:/";
    }
}

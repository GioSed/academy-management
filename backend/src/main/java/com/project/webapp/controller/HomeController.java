package com.project.webapp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    
    @GetMapping
    public String getHomePage(Model model) {
        model.addAttribute("welcomeMessage", "Welcome to the Academy Management System");
        return "home";
    }

    @GetMapping("/menu")
    public String getMenuPage(Model model) {
        model.addAttribute("menuItems", List.of("Student Registration", "Teacher Registration", "Show Students", "Show Teachers"));
        return "menu";
    }
}
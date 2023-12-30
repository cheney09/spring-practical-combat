package com.cheney.koala.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("index")
public class IndexController {
    @GetMapping
    public String index(Model model) {
        model.addAttribute("msg", "Welcome to Koala System.");
        return "index";
    }
}

package com.cheney.koala.controller;

import com.cheney.koala.model.User;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
@Slf4j
public class UserController {

    @GetMapping
    public String user() {
        return "Hello";
    }

    @PostMapping("register")
    public String registerUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.info("检验错误 " + result.getAllErrors().size() + " 个");
            result.getAllErrors().forEach(
                    e -> System.out.println(e.getDefaultMessage())
            );
            return "register";
        }

        log.info(user.toString());

        return "redirect:/login";
    }
}

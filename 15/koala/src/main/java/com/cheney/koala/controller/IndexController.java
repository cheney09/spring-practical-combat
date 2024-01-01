package com.cheney.koala.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("index")
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping
    public String index(Model model) {
        logger.debug("【Debug】- Hello.");
        logger.info("【Info】- Hello.");
        logger.warn("【Warn】- Hello.");
        logger.error("【Error】- Hello.");
        model.addAttribute("msg", "Welcome to Koala System.");
        return "index";
    }
}

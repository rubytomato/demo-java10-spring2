package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = {"/", ""})
@Slf4j
public class IndexController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("message", "hello world");
        return "index";
    }

}

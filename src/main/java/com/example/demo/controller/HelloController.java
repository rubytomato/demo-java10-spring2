package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "hello")
@Slf4j
public class HelloController {

  @GetMapping(path = "world")
  public String greeting() {
    return "hello world";
  }

}

package com.example.restapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BasicController {

    @RequestMapping("/")
    public String index(Model model){
        return "index";
    }
}

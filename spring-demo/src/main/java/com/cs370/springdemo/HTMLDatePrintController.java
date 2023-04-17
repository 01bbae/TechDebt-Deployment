package com.cs370.springdemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class HTMLDatePrintController {

    @RequestMapping("/html")
    public String sayHello(Model model) {
        model.addAttribute("date", new Date());
        return "example";
    }
}
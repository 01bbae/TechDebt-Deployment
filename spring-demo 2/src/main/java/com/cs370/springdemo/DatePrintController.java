package com.cs370.springdemo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class DatePrintController {

    @RequestMapping("/")
    public String sayHello() {
        return ("<p>Hello Sergey</p>" + String.format("Current date is: %s", new Date()));
    }
}
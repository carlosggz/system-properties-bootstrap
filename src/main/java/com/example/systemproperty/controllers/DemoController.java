package com.example.systemproperty.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.util.concurrent.Queues;

@RestController
@RequestMapping("/test")
public class DemoController {

    @GetMapping
    public String getSize() {
        return "Size is: " + Queues.SMALL_BUFFER_SIZE;
    }
}

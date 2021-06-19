package com.AssetArrange.CryptoAggregator.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    public static final String greeting = "Hello %s!";

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "world") final String name) {
        return String.format(greeting, name);
    }
}

package com.spring.example.tweater;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name,
                           Map<String, String> model) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping()
    public String main(Map<String, String> model) {
        model.put("some", "sdfsfsdfsdfs");
        model.put("name", "default");

        return "main";
    }

    public String test() {
        return "";
    }
}

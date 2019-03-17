package com.spring.example.tweater.controller;

import com.spring.example.tweater.domain.Message;
import com.spring.example.tweater.domain.User;
import com.spring.example.tweater.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String greeting(Map<String, String> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model) {

        Iterable<Message> allMessages;
        if (filter != null && !filter.isEmpty()) {
            allMessages = messageRepo.findByTag(filter);
        } else {
            allMessages = messageRepo.findAll();
        }
        model.addAttribute("messages", allMessages);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String addMessage(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            Model model) {

        Message message = new Message(text, tag, user);
        messageRepo.save(message);

        Iterable<Message> allMessages = messageRepo.findAll();
        model.addAttribute("messages", allMessages);
        model.addAttribute("filter", "");

        return "main";
    }
}

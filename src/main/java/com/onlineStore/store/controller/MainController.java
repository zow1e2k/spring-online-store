package com.onlineStore.store.controller;

import com.onlineStore.store.domain.Message;
import com.onlineStore.store.domain.User;
import com.onlineStore.store.repos.MessageRepo;
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

    @GetMapping("/main")
    public String main(Map<String, Object> model, @AuthenticationPrincipal User user) {
        Iterable<Message> messages = messageRepo.findAll();
        System.out.println(user != null ? user.getUsername() : "NULL");
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @RequestParam(name="tag", required=true, defaultValue="")
                    String tag,
            @RequestParam(name="text", required=true, defaultValue="")
                    String text,
            Model model,
            @AuthenticationPrincipal User user)
    {
        System.out.println(user != null ? user.getUsername() : "NULL");
        Message msg = new Message(tag, text);
        messageRepo.save(msg);

        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages", messages);
        return "main";
    }

    @PostMapping("filter")
    public String filter(
            @RequestParam(name="filter", required=true, defaultValue="")
                    String filter,
            Map<String, Object> model)
    {
        Iterable<Message> messages;

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }

        model.put("messages", messages);
        return "main";
    }

}


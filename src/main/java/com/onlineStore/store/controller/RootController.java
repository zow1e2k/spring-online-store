package com.onlineStore.store.controller;

import com.onlineStore.store.domain.Role;
import com.onlineStore.store.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {

    @RequestMapping
    public String root(@AuthenticationPrincipal User user, Model model) {
        if (user != null) {
            model.addAttribute("username", user.getUsername());

            if (user.getRoles().contains(Role.ADMIN)) {
                model.addAttribute("admin", true);
            } else if (user.getRoles().contains(Role.PRODUCER)) {
                model.addAttribute("producer", true);
            }
        }

        return "redirect:/products";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(@AuthenticationPrincipal User user, Model model) {

        if (user != null) {
            model.addAttribute("username", user.getUsername());

            if (user.getRoles().contains(Role.ADMIN)) {
                model.addAttribute("admin", true);
            } else if (user.getRoles().contains(Role.PRODUCER)) {
                model.addAttribute("producer", true);
            } else if (user.getRoles().contains(Role.MODERATOR)) {
                model.addAttribute("moderator", true);
            }
        }

        return "accessDenied";
    }

}

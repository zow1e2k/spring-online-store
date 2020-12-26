package com.onlineStore.store.controller;

import com.onlineStore.store.domain.Cart;
import com.onlineStore.store.domain.Role;
import com.onlineStore.store.domain.User;
import com.onlineStore.store.repos.CartRepo;
import com.onlineStore.store.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CartRepo cartRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        User userDB = userRepo.findByUsername(user.getUsername());

        if (userDB != null) {
            model.addAttribute("message", "Пользователь уже существует");
            return "registration";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(hashedPassword);
        user.setOnline(true);
        user.setRoles(Collections.singleton(Role.USER));
        Cart cart = new Cart();
        user.setCart(cart);
        cartRepo.save(cart);
        userRepo.save(user);

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

        return "redirect:/login";
    }
}

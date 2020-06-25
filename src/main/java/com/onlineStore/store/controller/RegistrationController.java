package com.onlineStore.store.controller;

import com.onlineStore.store.domain.Basket;
import com.onlineStore.store.domain.Role;
import com.onlineStore.store.domain.User;
import com.onlineStore.store.repos.BasketRepo;
import com.onlineStore.store.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BasketRepo basketRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userDB = userRepo.findByUsername(user.getUsername());

        if (userDB != null) {
            model.put("message", "User already exist");
            return "registration";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(hashedPassword);
        user.setOnline(true);
        user.setRoles(Collections.singleton(Role.USER));
        Basket basket = new Basket();
        user.setBasket(basket);
        basketRepo.save(basket);
        userRepo.save(user);

        return "redirect:/login";
    }
}

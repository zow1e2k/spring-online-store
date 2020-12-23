package com.onlineStore.store.controller;

import com.onlineStore.store.domain.*;
import com.onlineStore.store.repos.AddressDescRepo;
import com.onlineStore.store.repos.ProducerRepo;
import com.onlineStore.store.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/profile")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProducerRepo producerRepo;

    @Autowired
    private AddressDescRepo addressDescRepo;

    @GetMapping("/{user}")
    public String profile(
            Model model,
            @PathVariable String user,
            @AuthenticationPrincipal User user1
    ) {
        model.addAttribute("user", userRepo.findByUsername(user));

        if (user != null) {
            model.addAttribute("username", user1.getUsername());

            if (user1.getRoles().contains(Role.ADMIN)) {
                model.addAttribute("admin", true);
            }
        }

        return "user";
    }


    @GetMapping("/editor")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String users(
            @AuthenticationPrincipal User user,
            Model model) {

        model.addAttribute("username", user.getUsername());
        model.addAttribute("admin", true);
        model.addAttribute("users", userRepo.findAll());
        return "users";
    }


    @PostMapping("/editor/{user}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userEditForm(
        @PathVariable User user,
        Model model
    ) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("admin", true);
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping("/editor/save/{user}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @PathVariable User user,
        Model model) {

        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        
        userRepo.save(user);
        //Producer producer = new Producer(user);
        //producer.setRating(0.0f);
        //producerRepo.save(producer);

        model.addAttribute("username", user.getUsername());
        model.addAttribute("admin", true);

        return "redirect:/profile/editor";
    }


}

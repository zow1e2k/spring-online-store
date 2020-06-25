package com.onlineStore.store.controller;

import com.onlineStore.store.domain.Role;
import com.onlineStore.store.domain.User;
import com.onlineStore.store.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/profile")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/{user}")
    public String profile(
            Model model,
            @PathVariable User userPath,
            @AuthenticationPrincipal User user
    ) {
        if (userPath.equals(user)) {
            return "/root";
        }

        model.addAttribute("username", user.getUsername());
        model.addAttribute("access", user.getRoles());
        return "profile";
    }


    @GetMapping("/editor")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String users(
        Model model
    )
    {
        model.addAttribute("users", userRepo.findAll());
        return "users";
    }


    @PostMapping("/editor/{user}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userEditForm(
        @PathVariable User user,
        Model model
    ) {
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
        Model model
    ) {
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepo.save(user);

        return "redirect:/profile/editor";
    }


}

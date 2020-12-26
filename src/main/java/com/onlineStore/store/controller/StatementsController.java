package com.onlineStore.store.controller;

import com.onlineStore.store.domain.*;
import com.onlineStore.store.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/statements")
public class StatementsController {
    @Autowired
    private StatementRepo statementRepo;

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MODERATOR')")
    public String statements(Model model, @AuthenticationPrincipal User user) {
        Iterable<Statement> statements = statementRepo.findAll();

        model.addAttribute("statements", statements);

        if (user != null) {
            model.addAttribute("username", user.getUsername());

            if (user.getRoles().contains(Role.ADMIN)) {
                model.addAttribute("admin", true);
            } else if (user.getRoles().contains(Role.MODERATOR)) {
                model.addAttribute("moderator", true);
            }
        }
        return "statements";
    }

    @PostMapping("/setAgreed/{statement}")
    public String setAgreed(
            @PathVariable String statement,
            @AuthenticationPrincipal User user,
            Model model
    ) {
        Long id = Long.parseLong(statement);
        Statement statemnt = statementRepo.findStatementById(id);
        statemnt.changeAgreed();
        statemnt.setModerator(user);
        statementRepo.save(statemnt);

        if (user != null) {
            model.addAttribute("username", user.getUsername());

            if (user.getRoles().contains(Role.ADMIN)) {
                model.addAttribute("admin", true);
            } else if (user.getRoles().contains(Role.MODERATOR)) {
                model.addAttribute("moderator", true);
            }
        }

        return "redirect:/statements";
    }
}



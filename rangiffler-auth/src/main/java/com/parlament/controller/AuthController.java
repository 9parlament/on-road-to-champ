package com.parlament.controller;

import com.parlament.model.RegistrationForm;
import com.parlament.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static jakarta.servlet.http.HttpServletResponse.SC_CREATED;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/login")
    String getLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    String getRegisterPage(Model model) {
        model.addAttribute("registrationModel", new RegistrationForm());
        return "register";
    }

    @PostMapping("/register")
    public String processForm(
            @Valid @ModelAttribute("registrationModel") RegistrationForm form,
            BindingResult bindingResult,
            Model model,
            HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            response.setStatus(SC_BAD_REQUEST);
            return "register";
        }
        if (userService.isUserExist(form.getUsername())) {
            response.setStatus(SC_BAD_REQUEST);
            bindingResult.rejectValue("username", "duplicate", "Username must be unique");
            return "register";
        }
        userService.createUser(form);
        response.setStatus(SC_CREATED);
        model.addAttribute("frontUri", "http://localhost:3001");
        model.addAttribute("username", form.getUsername());
        return "register";
    }
}


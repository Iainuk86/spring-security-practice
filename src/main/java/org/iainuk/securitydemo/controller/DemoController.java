package org.iainuk.securitydemo.controller;

import org.iainuk.securitydemo.RegistrationForm;
import org.iainuk.securitydemo.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DemoController {

    private UserDetailsServiceImpl userDetailsService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DemoController(UserDetailsServiceImpl impl, PasswordEncoder pass) {
        this.userDetailsService = impl;
        this.passwordEncoder = pass;
    }

    @GetMapping("/register")
    public String registerForm(Model model) {

        model.addAttribute("form", new RegistrationForm());

        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute RegistrationForm form) {

        userDetailsService.save(form.toUser(passwordEncoder));

        return "home";
    }

//    public String functionThatUsesCurrentUser(Whatever we,
//                                              @AuthenticationPrincipal User user) {
//
//        Do whatever with user;
//
//        return "Whatever";
//    }

}

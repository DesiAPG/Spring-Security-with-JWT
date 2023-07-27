package com.example.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RolesController {
    @GetMapping("/access/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String accessAdmin() {
        return "Hello, admin";
    }

    @GetMapping("/access/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public String accessUser() {
        return "Hello, user";
    }

    @GetMapping("/access/guest")
    @PreAuthorize("hasAnyRole('USER','ADMIN','GUEST')")
    public String accessGuest() {
        return "Hello, guest";
    }
}

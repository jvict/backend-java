package com.brothers.festas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/profile")
    public ResponseEntity<String> getUserProfile() {
        return ResponseEntity.ok("Perfil do Usuário");
    }
}

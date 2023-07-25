package com.example.controller;

import com.example.controller.request.CreateUserDTO;
import com.example.models.Erole;
import com.example.models.RoleEntity;
import com.example.models.UserEntity;
import com.example.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class MainController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/hello")
    public String index() {
        return "Not secured";
    }

    @GetMapping("/hello/secured")
    public String indexSecured() {
        return "Secured";
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO userDTO) {

        Set<RoleEntity> roles = userDTO.getRoles()
                .stream()
                .map(role -> RoleEntity.builder()
                        .name(Erole.valueOf(role))
                        .build()).collect(Collectors.toSet());

        UserEntity userEntity = UserEntity.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .roles(roles)
                .build();
        userRepository.save(userEntity);
        return ResponseEntity.ok(userEntity);
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam String id) {
        userRepository.deleteById(Long.parseLong(id));
        return "User deleted successfully";
    }
}

package com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class RoleUserController {

    @GetMapping
    public ResponseEntity<?> testRoleUser(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok("Hello " + userDetails.getUsername() + "!");
    }

}

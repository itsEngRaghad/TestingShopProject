package com.example.securityshop.Controller;

import com.example.securityshop.Model.User;
import com.example.securityshop.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(name = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers(){
        List<User> users=authService.getAllUsers();
        return ResponseEntity.status(200).body(users);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user){

        authService.register(user);
        return ResponseEntity.status(200).body("user registered");
    }

//    @PostMapping("/admin")
//    public ResponseEntity admin(){
//
//        return ResponseEntity.status(200).body("welcome admin");
//    }
//
//    @PostMapping("/user")
//    public ResponseEntity user(){
//
//        return ResponseEntity.status(200).body("welcome user");
//    }

    @PostMapping("/login")
    public ResponseEntity login(){

        return ResponseEntity.status(200).body("you logged in");
    }

    @PostMapping("/logout")
    public ResponseEntity logout(){

        return ResponseEntity.status(200).body("logging out succeed");
    }
}

package com.example.securityshop.Service;

import com.example.securityshop.Model.User;
import com.example.securityshop.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    public List<User> getAllUsers()
    {
        List<User> users= authRepository.findAll();
        return users;
    }

    public void register(User user){
        user.setRole("CUSTOMER"); //to set customer role by default, don't let users make themselves as an admins

        String hash=new BCryptPasswordEncoder().encode(user.getPassword()); //encrypt password first by hashing
        // then set it to save
        user.setPassword(hash); //regular saving
        authRepository.save(user);
    }
}

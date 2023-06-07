package com.example.securityshop.Service;

import com.example.securityshop.Model.User;
import com.example.securityshop.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final AuthRepository authRepository; //to arrive to DB

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=authRepository.findUserByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("wrong username or password");
        }
        return user;
    }
}

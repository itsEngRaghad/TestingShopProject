package com.example.securityshop;

import com.example.securityshop.Model.Order;
import com.example.securityshop.Model.User;
import com.example.securityshop.Repository.AuthRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthRepositoryTest {

    @Autowired
    AuthRepository myUserRepository;

    User myUser;


    @BeforeEach
    void setUp(){
        myUser=new User(null,"username","password","ADMIN",null);
    }

    @Test
    public void findUserById(){
        myUserRepository.save(myUser);
        User user=myUserRepository.findUserById(myUser.getId());
        Assertions.assertThat(user).isEqualTo(myUser);
    }

//    findUserByUsername

    @Test
    public void findUserByUsername(){
        myUserRepository.save(myUser);
        User user=myUserRepository.findUserByUsername(myUser.getUsername());
        Assertions.assertThat(user).isEqualTo(myUser);
    }
}

package com.example.securityshop;

import com.example.securityshop.Model.Order;
import com.example.securityshop.Model.User;
import com.example.securityshop.Repository.AuthRepository;
import com.example.securityshop.Repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    AuthRepository myUserRepository;

    Order order1,order2,order3;
    User myUser;


    @BeforeEach
    void setUp(){
        myUser=new User(null,"username","password","ADMIN",null);
        order1=new Order(null,2,2,2,"completed",myUser,null);
        order2=new Order(null,2,2,2,"completed",myUser,null);
        order3=new Order(null,2,2,2,"completed",myUser,null);

    }

    @Test
    public void findAllByUser(){
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
        List<Order>orders=orderRepository.findAllByUser(myUser);
        Assertions.assertThat(orders.get(0).getUser().getId()).isEqualTo(myUser.getId());

    }
//        @Test
//        public void findOrderByUserId(){
//        orderRepository.save(order1);
//        Order order=orderRepository.findOrderByUserId(myUser.getId());
//        Assertions.assertThat(order.getUser().getId()).isEqualTo(myUser.getId());
//        }
    @Test
    public void findOrderById(){
        orderRepository.save(order1);
        Order order=orderRepository.findOrderById(order1.getId());
        Assertions.assertThat(order).isEqualTo(order1);
    }

}

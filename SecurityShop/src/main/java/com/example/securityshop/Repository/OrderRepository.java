package com.example.securityshop.Repository;

import com.example.securityshop.Model.Order;
import com.example.securityshop.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer>{


    Order findOrderById(Integer id);
    List<Order>findOrderByUserId(Integer id);

    List<Order> findAllByUser(User user);
}

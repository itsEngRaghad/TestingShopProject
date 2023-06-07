package com.example.securityshop;

import com.example.securityshop.Model.Order;
import com.example.securityshop.Model.User;
import com.example.securityshop.Repository.AuthRepository;
import com.example.securityshop.Repository.OrderRepository;
import com.example.securityshop.Service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class OrderServiceTest {

    @InjectMocks
    OrderService orderService;

    @InjectMocks
    OrderRepository orderRepository;
    @Mock
    AuthRepository authRepository;

    User user;

    Order order1,order2,order3;
    List<Order>orders;

    @BeforeEach
    void setUp(){
        user=new User(null,"username","password","ADMIN",null);
        order1=new Order(null,2,2,2,"completed",user,null);
        order2=new Order(null,2,2,2,"completed",user,null);
        order3=new Order(null,2,2,2,"completed",user,null);

        orders=new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
    }

    @Test
    public void getAllOrders(){
        when(orderRepository.findAll()).thenReturn(orders);
        List<Order> orderList=orderService.getAllOrders();
        Assertions.assertEquals(orderList,orders);
        Assertions.assertEquals(3,orderList.size());
        verify(orderRepository,times(1)).findAll();

    }

    @Test
    public void getOrders(){ //return list todo by user
        when(authRepository.findUserById(user.getId())).thenReturn(user);
        when(orderRepository.findAllByUser(user)).thenReturn(orders);//return list of user


        List<Order> orderList = orderService.getOrders(user.getId());
//        Assertions.assertEquals(todo,todos);
        Assertions.assertEquals(orderList.get(0).getUser().getId(),user.getId());
        verify(authRepository,times(1)).findUserById(user.getId());
        verify(orderRepository,times(1)).findAllByUser(user);

    }

    @Test
    public void addOrderTest(){

        when(authRepository.findUserById(user.getId())).thenReturn(user);

        orderService.addOrder(user.getId(),order3);
        verify(authRepository,times(1)).findUserById(user.getId());
        verify(orderRepository,times(1)).save(order3);
    }

    @Test
    public void updateOrder(){

        when(orderRepository.findOrderById(order1.getId())).thenReturn(order1);
        when(authRepository.findUserById(user.getId())).thenReturn(user);

        orderService.updateOrder(order1.getId(),order1,user.getId());

        verify(orderRepository,times(1)).findOrderById(order1.getId());
        verify(authRepository,times(1)).findUserById(user.getId());
        verify(orderRepository,times(1)).save(order1);

    }

    @Test
    public void deleteOrder(){
        when(orderRepository.findOrderById(order2.getId())).thenReturn(order2);

        orderService.deleteOrder(order2.getId(),user.getId());

        verify(orderRepository,times(1)).findOrderById(order2.getId());
        verify(orderRepository,times(1)).delete(order2);

    }

}

package com.example.securityshop;

import com.example.securityshop.Model.Order;
import com.example.securityshop.Model.Product;
import com.example.securityshop.Model.User;
import com.example.securityshop.Repository.OrderRepository;
import com.example.securityshop.Repository.ProductRepository;
import com.example.securityshop.Service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class ProductServiceTest {

    @InjectMocks
    ProductRepository productRepository;

    @InjectMocks
    OrderRepository orderRepository;

    @InjectMocks
    ProductService productService;

    User user;

    Product product1,product2,product3;
    List<Product> products;
    Order order;



    @BeforeEach
    void setUp(){
        order=new Order(null,2,2,2,"completed",null,null);
        product1=new Product(null,"name",20,null);
        product2=new Product(null,"name",20,null);
        product3=new Product(null,"name",20,null);

        products=new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product2);
    }


    @Test
    public void getAllProducts(){
        when(productRepository.findAll()).thenReturn(products);
        List<Product> productList=productService.getAllProducts();
        Assertions.assertEquals(productList,products);
        Assertions.assertEquals(3,productList.size());
        verify(orderRepository,times(1)).findAll();

    }



}

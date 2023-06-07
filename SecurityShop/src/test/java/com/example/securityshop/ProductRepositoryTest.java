package com.example.securityshop;

import com.example.securityshop.Model.Order;
import com.example.securityshop.Model.Product;
import com.example.securityshop.Model.User;
import com.example.securityshop.Repository.AuthRepository;
import com.example.securityshop.Repository.OrderRepository;
import com.example.securityshop.Repository.ProductRepository;
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
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    Product product1,product2,product3;
    Order order;

    @BeforeEach
    void setUp(){
        order=new Order(null,2,2,2,"completed",null,null);
        product1=new Product(null,"name",20,null);
        product2=new Product(null,"name",20,null);
        product3=new Product(null,"name",20,null);


    }


    @Test
    public void findProductById(){
        productRepository.save(product1);
        Product product=productRepository.findProductById(product1.getId());
        Assertions.assertThat(product).isEqualTo(product1);
    }


}

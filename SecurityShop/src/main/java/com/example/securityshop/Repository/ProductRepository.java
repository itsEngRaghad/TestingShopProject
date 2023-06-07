package com.example.securityshop.Repository;

import com.example.securityshop.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    Product findProductById(Integer id);

}

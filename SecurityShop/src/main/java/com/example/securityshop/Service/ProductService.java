package com.example.securityshop.Service;

import com.example.securityshop.APIException.APIException;
import com.example.securityshop.Model.Order;
import com.example.securityshop.Model.Product;
import com.example.securityshop.Model.User;
import com.example.securityshop.Repository.AuthRepository;
import com.example.securityshop.Repository.OrderRepository;
import com.example.securityshop.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final AuthRepository authRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


    //---------------------CRUD----------------------------------

    //get
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    //add

    public void addProduct(Product product){
        productRepository.save(product);
    }

    //update

    public void updateProduct(Integer id, Product product){
        Product oldProduct=productRepository.findProductById(id);
        if(oldProduct==null){
            throw new APIException("Product not found, sorry :( ");
        }

        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        productRepository.save(oldProduct);
    }


    //delete


    public void deleteBlog(Integer id){

        Product oldProduct=productRepository.findProductById(id);
        if(oldProduct==null){
            throw new APIException("Product not found, sorry :( ");
        }
        productRepository.deleteById(id);
    }


    //-------------Other EndPoints---------------------------

    //get Product By id

    public Product findProductById(Integer id){
        Product prID=productRepository.findProductById(id);
        if(prID==null){
            throw new APIException("Sorry, We Couldn't find the Product your looking for, Try another ID!");
        }
        return prID;
    }





}

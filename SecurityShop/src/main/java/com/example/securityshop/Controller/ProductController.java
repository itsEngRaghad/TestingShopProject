package com.example.securityshop.Controller;

import com.example.securityshop.Model.Product;
import com.example.securityshop.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/api/v1/product")
@RequiredArgsConstructor

public class ProductController {

    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getAllProducts(){
        List<Product> products=productService.getAllProducts();
        return ResponseEntity.status(200).body(products);
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product){
        productService.addProduct(product);
        return ResponseEntity.status(200).body("Your Product has been Successfully added!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@Valid@RequestBody Product product, @PathVariable Integer id){
        productService.updateProduct(id,product);
        return ResponseEntity.status(200).body("Your Blog has been Successfully updated!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id){
        productService.deleteBlog(id);
        return ResponseEntity.status(200).body("Sadly, Your Product Has been Deleted ");
    }

    @GetMapping("/get-id/{id}")
    public ResponseEntity findProductById(@PathVariable Integer id){
        Product product= productService.findProductById(id);
        return ResponseEntity.status(200).body(product);
    }
}

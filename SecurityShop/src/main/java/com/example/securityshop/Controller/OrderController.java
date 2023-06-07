package com.example.securityshop.Controller;

import com.example.securityshop.Model.Order;
import com.example.securityshop.Model.Product;
import com.example.securityshop.Model.User;
import com.example.securityshop.Repository.ProductRepository;
import com.example.securityshop.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    //initialization
    private final OrderService orderService;

    //------------------Assign---------------------


    @PutMapping("/assign/{product_id}/{order_id}")
    public ResponseEntity assignProductToOrder(@PathVariable Integer product_id, @PathVariable Integer order_id){
        orderService.assignProductToOrder(order_id, product_id);
        return ResponseEntity.status(200).body("assign done");
    }


    @PutMapping("/assign/{user_id}/{order_id}")
    public ResponseEntity assignOrderToCustomer(@PathVariable Integer user_id, @PathVariable Integer order_id){
        orderService.assignOrderToCustomer(order_id, user_id);
        return ResponseEntity.status(200).body("assign done");
    }

    //-----------------CRUD---------------------------

    @GetMapping("/get")
    public ResponseEntity getOrders(@AuthenticationPrincipal User user){
        //@AuthenticationPrincipal to get the id of the user while logging in to get the list of the specific user
        List<Order> orders=orderService.getOrders(user.getId());
        return ResponseEntity.status(200).body(orders);
    }
    @PostMapping("/add")
    public ResponseEntity addOrder(@AuthenticationPrincipal User user, @RequestBody Order order){
        // we have to add to do inside this special user, we take todo and myuser to add in
        orderService.addOrder(user.getId(),order);
        return ResponseEntity.status(200).body("Order Generated successfully!");
    }


    @PutMapping("/update/{orderid}")
    public ResponseEntity updateToDo(@AuthenticationPrincipal User user, @RequestBody Order order,@PathVariable Integer orderid){

        orderService.updateOrder(user.getId(), order,orderid);
        return ResponseEntity.status(200).body("Order Updated!");
    }

    @DeleteMapping("/delete/{orderid}")
    public ResponseEntity deleteToDo(@AuthenticationPrincipal User user, @PathVariable Integer orderid){
        orderService.deleteOrder(user.getId(),orderid); //.getId();
        return ResponseEntity.status(200).body("Order Deleted!");
    }

    @GetMapping("/get")
    public ResponseEntity getTodo(@AuthenticationPrincipal User myUser){

        List<Order> todoList=orderService.getOrderx(myUser.getId());
        return ResponseEntity.status(200).body(todoList);
    }


    //--------------Other EndPoints-------------------

    @GetMapping("/get-allblog")
    public ResponseEntity getAllBlogs(){
        List<Order> orders=orderService.getAllOrders();
        return ResponseEntity.status(200).body(orders);
    }


    @GetMapping("/get-id/{id}")
    public ResponseEntity findById(@PathVariable Integer id){
        Order order= orderService.findOrderById(id);
        return ResponseEntity.status(200).body(order);
    }

    @PutMapping("/update-status/{orderid}/{status}")
    public ResponseEntity updateStatus(@AuthenticationPrincipal User user,@PathVariable Integer orderid,@PathVariable String status){

        orderService.updateStatus(user.getId() ,orderid,status);
        return ResponseEntity.status(200).body("Status Updated!");
    }


}

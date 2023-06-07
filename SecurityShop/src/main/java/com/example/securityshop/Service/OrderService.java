package com.example.securityshop.Service;

import com.example.securityshop.APIException.APIException;
import com.example.securityshop.Model.Order;
import com.example.securityshop.Model.Product;
import com.example.securityshop.Model.User;
import com.example.securityshop.Repository.AuthRepository;
import com.example.securityshop.Repository.OrderRepository;
import com.example.securityshop.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final AuthRepository authRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    //----------------assign Order to Customer-----------------------
    public void assignOrderToCustomer(Integer user_id,Integer order_id){
        //check if they both exist
        User user=authRepository.findUserById(user_id);
        Order order=orderRepository.findOrderById(order_id);
        if(user==null||order==null){
            throw new APIException("can't assign Order, wrong id");
        }
        order.setUser(user);
        orderRepository.save(order);
    }

    //----------------assign Product to Order-----------------------
    public void assignProductToOrder(Integer product_id,Integer order_id){
        //check if they both exist
        Product product=productRepository.findProductById(product_id);
        Order order=orderRepository.findOrderById(order_id);
        if(product_id==null||order==null){
            throw new APIException("can't assign Product, wrong id");
        }
        order.setProduct(product);
        orderRepository.save(order);
//        product.setOrderSet((Set<Order>) order);
//        productRepository.save(product);

    }

    //------------End Of Assigning-------------------------------




    //---------------------CRUD----------------------------------

    public List<Order> getOrders(Integer userId) {
        return orderRepository.findOrderByUserId(userId);
    }



    //    In Add order endpoint: calculate the total price , status by defalut new
    public void addOrder(Integer userId, Order order){
        Product product=productRepository.findProductById(order.getProduct().getId()); //smth wrong not user id check again
        User user=authRepository.findUserById(userId);
        order.setUser(user);
        order.setStatus("new");
        order.setTotalprice(order.getQuantity()* product.getPrice());
        orderRepository.save(order);
    }


    public void updateOrder(Integer userId, Order order, Integer orderid){

        Order oldOrder=orderRepository.findOrderById(orderid);
        if(oldOrder==null) {
            throw new APIException("sorry no such Order to update");
        }

        if(oldOrder.getUser().getId()!=userId) {
            throw new APIException("unAuthorized, this Order doesn't belong to you!");
        }

        oldOrder.setQuantity(order.getQuantity());
//        oldOrder.setProduct(order.getProduct());
//        oldOrder.setStatus(order.getStatus());
        oldOrder.setDatereceived(order.getDatereceived());
        orderRepository.save(oldOrder);
    }


    //In Delete endpoint, check order status if its in progress or complete throw an exception
    public void deleteOrder(Integer userId, Integer orderid){
        //check if Order  exist

        Order oldOrder=orderRepository.findOrderById(orderid);
        if(oldOrder==null) {
            throw new APIException("sorry no such Order to Delete");
        }

        if(oldOrder.getUser().getId()!=userId) {
            throw new APIException("unAuthorized, this Order doesn't belong to you!");
        }

        if(oldOrder.getStatus().equals("inProgress")||oldOrder.getStatus().equals("completed")) {
            throw new APIException("You Can't Delete This Order it's Even Completed or already in progress");
        }
        //else, if found
        orderRepository.delete(oldOrder);
    }


    //-------------Other EndPoints---------------------------

    //Get All user blogs
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }


    //Get blog by Id

    public Order findOrderById(Integer id){
        Order order=orderRepository.findOrderById(id);
        if(order==null){
            throw new APIException("Sorry, We Couldn't find the Order your looking for, Try another ID!");
        }
        return order;
    }



    //Create endpoint that change order status(only admin can change it)

    public void updateStatus(Integer userId, Integer orderid,String status){

        Order oldOrder=orderRepository.findOrderById(orderid);
        if(oldOrder==null) {
            throw new APIException("sorry no such Order to update");
        }

        if(oldOrder.getUser().getId()!=userId) {
            throw new APIException("unAuthorized, this Order doesn't belong to you!");
        }

        oldOrder.setStatus(status);
        orderRepository.save(oldOrder);
    }


    public List<Order> getOrderx( Integer userId) {
        User user=authRepository.findUserById(userId);
        return orderRepository.findAllByUser(user);
    }



}

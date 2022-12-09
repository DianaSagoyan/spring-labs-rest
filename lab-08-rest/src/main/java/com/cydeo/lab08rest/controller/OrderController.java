package com.cydeo.lab08rest.controller;

import com.cydeo.lab08rest.dto.OrderDTO;
import com.cydeo.lab08rest.enums.PaymentMethod;
import com.cydeo.lab08rest.model.ResponseWrapper;
import com.cydeo.lab08rest.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> getAddressList(){
        return ResponseEntity.ok(new ResponseWrapper("Orders successfully retrieved", orderService.getAddressList(), HttpStatus.OK));
    }

    @GetMapping("/paymentMethod/{paymentMethod}")
    public ResponseEntity<ResponseWrapper> getOrderListByPaymentMethod(@PathVariable("paymentMethod")PaymentMethod paymentMethod){
        return ResponseEntity.ok(new ResponseWrapper("Orders successfully retrieved by payment method", orderService.getOrderListByPaymentMethod(paymentMethod), HttpStatus.OK));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseWrapper> getOrderListByEmail(@PathVariable("email")String email){
        return ResponseEntity.ok(new ResponseWrapper("Orders successfully retrieved by email", orderService.getOrderListByEmail(email), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper> createOrder(@RequestBody OrderDTO order){
        return ResponseEntity.ok(new ResponseWrapper("Order successfully created", orderService.createOrder(order), HttpStatus.OK));
    }

    @PutMapping
    public ResponseEntity<ResponseWrapper> updateOrder(@RequestBody OrderDTO order){
        return ResponseEntity.ok(new ResponseWrapper("Order successfully updated", orderService.update(order), HttpStatus.OK));
    }
}

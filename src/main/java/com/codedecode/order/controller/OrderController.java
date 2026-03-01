package com.codedecode.order.controller;


import com.codedecode.order.dto.OrderDTO;
import com.codedecode.order.dto.OrderDTOFromFE;
import com.codedecode.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/saveOrder")
    public ResponseEntity<OrderDTO> saveOrder(@RequestBody OrderDTOFromFE orderDetails){
        OrderDTO orderSavedInDB = orderService.saveOrderInDb(orderDetails);
        return new ResponseEntity<>(orderSavedInDB, HttpStatus.CREATED);
    }



}

package com.codedecode.order.service;
import com.codedecode.order.client.ReviewClient;
import com.codedecode.order.dto.ReviewDTO;
import java.util.List;
import com.codedecode.order.dto.OrderDTO;
import com.codedecode.order.dto.OrderDTOFromFE;
import com.codedecode.order.entity.Order;
import com.codedecode.order.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ReviewClient reviewClient;

    public List<ReviewDTO> getReviews(Integer foodId) {
        return reviewClient.getReviewsByFoodId(foodId);
    }
    
    public OrderDTO saveOrderInDb(OrderDTOFromFE orderDetails) {

        Order order = new Order();
        order.setUserId(orderDetails.getUserId());

        Order saved = orderRepo.save(order);

        OrderDTO dto = new OrderDTO();
        dto.setOrderId(saved.getOrderId());

        return dto;
    }
    
    
}
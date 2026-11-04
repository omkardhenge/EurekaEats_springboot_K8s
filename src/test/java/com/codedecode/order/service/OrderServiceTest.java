package com.codedecode.order.service;

import com.codedecode.order.client.ReviewClient;
import com.codedecode.order.dto.OrderDTO;
import com.codedecode.order.dto.OrderDTOFromFE;
import com.codedecode.order.dto.ReviewDTO;
import com.codedecode.order.entity.Order;
import com.codedecode.order.repo.OrderRepo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepo orderRepo;

    @Mock
    private ReviewClient reviewClient;

    @InjectMocks
    private OrderService orderService;


    // Test 1: Save Order Successfully
    @Test
    void saveOrderInDb_shouldReturnOrderDTO() {

        // Arrange
        OrderDTOFromFE orderDetails = new OrderDTOFromFE();
        orderDetails.setUserId(101);

        Order savedOrder = new Order();
        savedOrder.setOrderId(1);
        savedOrder.setUserId(101);

        when(orderRepo.save(any(Order.class)))
                .thenReturn(savedOrder);

        // Act
        OrderDTO result =
                orderService.saveOrderInDb(orderDetails);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getOrderId());

        verify(orderRepo, times(1))
                .save(any(Order.class));
    }


    // Test 2: Get Reviews Successfully
    @Test
    void getReviews_shouldReturnReviews() {

        // Arrange
        Integer foodId = 10;

        List<ReviewDTO> reviews = Arrays.asList(
                new ReviewDTO(),
                new ReviewDTO()
        );

        when(reviewClient.getReviewsByFoodId(foodId))
                .thenReturn(reviews);

        // Act
        List<ReviewDTO> result =
                orderService.getReviews(foodId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertSame(reviews, result);

        verify(reviewClient, times(1))
                .getReviewsByFoodId(foodId);
    }
}

package com.codedecode.order.client;

import com.codedecode.order.dto.ReviewDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "review-service")
public interface ReviewClient {

    @GetMapping("/reviews/food/{foodId}")
    List<ReviewDTO> getReviewsByFoodId(@PathVariable("foodId") Integer foodId);

}
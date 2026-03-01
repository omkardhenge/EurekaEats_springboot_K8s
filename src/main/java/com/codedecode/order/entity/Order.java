package com.codedecode.order.entity;


import com.codedecode.order.dto.FoodItemDTO;
import com.codedecode.order.dto.Restaurant;
import com.codedecode.order.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("order")
public class Order {
    private Integer orderId;
    private List<FoodItemDTO> foodItemsList;
    private Restaurant restaurant;
    private UserDTO userDTO;

}

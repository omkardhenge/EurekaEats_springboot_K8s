package com.codedecode.order.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "foodItems")
public class FoodItem {
    @Id
    private String id;
    private String itemName;
    private String itemDescription;
    private boolean isVeg;
    private Double price;
    private String imageUrl;
    private Integer restaurantId;
    private Integer quantity;
}
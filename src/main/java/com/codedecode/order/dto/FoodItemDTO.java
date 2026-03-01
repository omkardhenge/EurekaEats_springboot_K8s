package com.codedecode.order.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemDTO {
    private String id;
    private String itemName;
    private String itemDescription;
    private boolean isVeg;
    private Double price;
    private String imageUrl;
    private Integer restaurantId;
    private Integer quantity;
}
package com.codedecode.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemDTO {

    private Integer id;

    private String itemName;

    private String itemDescription;

    private boolean veg;

    private Double price;

    private String imageUrl;

    private Integer quantity;
}
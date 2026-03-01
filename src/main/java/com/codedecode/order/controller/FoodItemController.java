package com.codedecode.order.controller;

import com.codedecode.order.dto.FoodItemDTO;
import com.codedecode.order.entity.FoodItem;
import com.codedecode.order.repo.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/food")
@CrossOrigin
public class FoodItemController {

    @Autowired
    private FoodItemRepository foodItemRepository;

    @PostMapping("/items")
    public ResponseEntity<List<FoodItemDTO>> getAllFoodItems() {
        List<FoodItem> items = foodItemRepository.findAll();
        List<FoodItemDTO> dtos = items.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<FoodItemDTO> addFoodItem(@RequestBody FoodItemDTO foodItemDTO) {
        FoodItem item = convertToEntity(foodItemDTO);
        FoodItem saved = foodItemRepository.save(item);
        return new ResponseEntity<>(convertToDTO(saved), HttpStatus.CREATED);
    }

    private FoodItemDTO convertToDTO(FoodItem item) {
        FoodItemDTO dto = new FoodItemDTO();
        dto.setId(item.getId());
        dto.setItemName(item.getItemName());
        dto.setItemDescription(item.getItemDescription());
        dto.setVeg(item.isVeg());
        dto.setPrice(item.getPrice());
        dto.setImageUrl(item.getImageUrl());
        dto.setRestaurantId(item.getRestaurantId());
        dto.setQuantity(item.getQuantity() != null ? item.getQuantity() : 1);
        return dto;
    }

    private FoodItem convertToEntity(FoodItemDTO dto) {
        FoodItem item = new FoodItem();
        item.setId(dto.getId());
        item.setItemName(dto.getItemName());
        item.setItemDescription(dto.getItemDescription());
        item.setVeg(dto.isVeg());
        item.setPrice(dto.getPrice());
        item.setImageUrl(dto.getImageUrl());
        item.setRestaurantId(dto.getRestaurantId());
        item.setQuantity(dto.getQuantity());
        return item;
    }
}
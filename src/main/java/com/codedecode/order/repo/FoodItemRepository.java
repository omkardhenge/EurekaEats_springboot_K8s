package com.codedecode.order.repo;

import com.codedecode.order.entity.FoodItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepository extends MongoRepository<FoodItem, String> {
}
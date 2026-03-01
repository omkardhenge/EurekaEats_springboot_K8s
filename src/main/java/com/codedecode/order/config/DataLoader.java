package com.codedecode.order.config;

import com.codedecode.order.entity.FoodItem;
import com.codedecode.order.repo.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Override
    public void run(String... args) throws Exception {
       
        foodItemRepository.deleteAll();

		List<FoodItem> items = Arrays.asList(
				new FoodItem(null, "Burger", "Juicy grilled chicken burger with lettuce and mayo", false, 150.0,
						"https://images.unsplash.com/photo-1550547660-d9450f859349?w=600&q=80&auto=format&fit=crop", 1,
						1),
				new FoodItem(null, "Pizza", "Classic Margherita with fresh basil", true, 300.0,
						"https://images.unsplash.com/photo-1513104890138-7c749659a591?w=600&auto=format&fit=crop", 1,
						1),
				new FoodItem(null, "Pasta", "Creamy Alfredo pasta with mushrooms", true, 250.0,
						"https://images.unsplash.com/photo-1621996346565-e3dbc646d9a9?w=600&auto=format&fit=crop",
						1, 1),
				new FoodItem(null, "Fries", "Crispy golden fries with sea salt", true, 100.0,
						"https://images.unsplash.com/photo-1576107232684-1279f390859f?w=600&q=80&auto=format&fit=crop",
						1, 1),
				new FoodItem(null, "Double Burger", "Double patty with cheese and bacon", false, 150.0,
						"https://plus.unsplash.com/premium_photo-1668025335051-98c22e32cbba?w=600&auto=format&fit=crop", 2,
						1),
				new FoodItem(null, "Samosa", "Crispy pastry filled with spiced potatoes", true, 300.0,
						"https://images.unsplash.com/photo-1732519970445-8f2d6998961f?w=600&auto=format&fit=crop", 2,
						1),
				new FoodItem(null, "Dosa", "South Indian crispy crepe with chutney", true, 250.0,
						"https://images.unsplash.com/photo-1694849789325-914b71ab4075?w=600&auto=format&fit=crop", 2,
						1),
				new FoodItem(null, "Misal Pav", "Spicy sprouted curry with bread", true, 100.0,
						"https://images.unsplash.com/photo-1619193099598-6856ec4e2a87?w=600&auto=format&fit=crop", 2,
						1),
				new FoodItem(null, "Chicken Tacos", "Soft corn tortillas with spiced chicken, salsa, and lime", false,
						220.0,
						"https://images.unsplash.com/photo-1599974579688-8dbdd335c77f?w=600&auto=format&fit=crop", 1,
						1),
				new FoodItem(null, "Sushi Platter", "Assorted fresh sushi with salmon, tuna, and avocado", false, 450.0,
						"https://images.unsplash.com/photo-1553621042-f6e147245754?w=600&auto=format&fit=crop", 2, 1),
				new FoodItem(null, "Pad Thai", "Stir-fried rice noodles with tofu, peanuts, and bean sprouts", true,
						280.0, "https://images.unsplash.com/photo-1559314809-0d155014e29e?w=600&auto=format&fit=crop",
						1, 1),
				new FoodItem(null, "Gulab Jamun", "Soft milk solids dumplings soaked in rose-flavored sugar syrup",
						true, 120.0,
						"https://images.unsplash.com/photo-1666190092159-3171cf0fbb12?w=600&auto=format&fit=crop", 2,
						1),
				new FoodItem(null, "Butter Croissant", "Flaky, buttery French pastry, perfect for breakfast", true,
						150.0, "https://images.unsplash.com/photo-1555507036-ab1f4038808a?w=600&auto=format&fit=crop",
						1, 1),
				new FoodItem(null, "Tonkotsu Ramen", "Rich pork bone broth with chashu, egg, and noodles", false, 380.0,
						"https://images.unsplash.com/photo-1557872943-16a5ac26437e?w=600&auto=format&fit=crop", 2, 1),
				new FoodItem(null, "Falafel Wrap", "Crispy chickpea balls with hummus, veggies, and tahini in pita",
						true, 200.0,
						"https://images.unsplash.com/photo-1681072530653-db8fe2538631?w=600&auto=format&fit=crop", 1,
						1),
				new FoodItem(null, "Chocolate Brownie", "Rich, fudgy chocolate brownie with walnuts", true, 130.0,
						"https://images.unsplash.com/photo-1606313564200-e75d5e30476c?w=600&auto=format&fit=crop", 2,
						1));

        foodItemRepository.saveAll(items);
        System.out.println("Sample food items loaded successfully.");
    }
}
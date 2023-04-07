package com.sumanth.strategy.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.sumanth.constants.Constants.Cuisine;
import com.sumanth.objects.CostTracking;
import com.sumanth.objects.CuisineTracking;
import com.sumanth.objects.Restaurant;
import com.sumanth.objects.User;
import com.sumanth.strategy.RecommendationStrategy;

public class FeaturedRestaurantRecommendationStrategy implements RecommendationStrategy{

	@Override
	public List<Restaurant> getRecommendations(User user, List<Restaurant> availableRestaurants,
			CuisineTracking primaryCuisineTracking, CostTracking primaryCostTracking,
			List<Cuisine> secondaryCuisineList, List<Integer> secondaryCostList, int maxRecommendedRestaurants) {
        List<Restaurant> recommendedRestaurants = new ArrayList<>();
        recommendedRestaurants = availableRestaurants.stream()
                .filter(restaurant -> restaurant.getCuisine() == primaryCuisineTracking.getType() && restaurant.getCostBracket() == primaryCostTracking.getType())
                .sorted(Comparator.comparing(Restaurant::getRating).reversed())
                .collect(Collectors.toList());      
        
        if(recommendedRestaurants.isEmpty()) {
        	recommendedRestaurants = availableRestaurants.stream()
                    .filter(restaurant -> (restaurant.getCuisine() == primaryCuisineTracking.getType() && secondaryCostList.contains(restaurant.getCostBracket())))
                    .sorted(Comparator.comparing(Restaurant::getRating).reversed())
                    .collect(Collectors.toList());
        	
        	recommendedRestaurants = availableRestaurants.stream()
                    .filter(restaurant -> (secondaryCuisineList.contains(restaurant.getCuisine()) && restaurant.getCostBracket() == primaryCostTracking.getType()))
                    .sorted(Comparator.comparing(Restaurant::getRating).reversed())
                    .collect(Collectors.toList());
        }
        return recommendedRestaurants;
    }

}

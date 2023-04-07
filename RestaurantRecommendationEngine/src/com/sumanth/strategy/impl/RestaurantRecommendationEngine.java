package com.sumanth.strategy.impl;

import com.sumanth.strategy.RecommendationStrategy;
import com.sumanth.constants.Constants.Cuisine;
import com.sumanth.objects.*;
import java.util.*;

public class RestaurantRecommendationEngine {

	private RecommendationStrategy recommendationStrategy;

	public RestaurantRecommendationEngine(RecommendationStrategy recommendationStrategy) {
		this.recommendationStrategy = recommendationStrategy;
	}

	public List<Restaurant> getRestaurantRecommendations(User user, List<Restaurant> availableRestaurants,
			CuisineTracking primaryCuisineTracking, CostTracking primaryCostTracking,
			List<Cuisine> secondaryCuisineList, List<Integer> secondaryCostList, int maxRecommendedRestaurants) {
		return recommendationStrategy.getRecommendations(user, availableRestaurants, primaryCuisineTracking,
				primaryCostTracking, secondaryCuisineList, secondaryCostList, maxRecommendedRestaurants);
	}
}

package com.sumanth.strategy;

import com.sumanth.constants.Constants.Cuisine;
import com.sumanth.objects.*;
import java.util.*;

public interface RecommendationStrategy {

	List<Restaurant> getRecommendations(User user, List<Restaurant> availableRestaurants,
			CuisineTracking primaryCuisineTracking, CostTracking primaryCostTracking,
			List<Cuisine> secondaryCuisineList, List<Integer> secondaryCostList, int maxRecommendedRestaurants);
}

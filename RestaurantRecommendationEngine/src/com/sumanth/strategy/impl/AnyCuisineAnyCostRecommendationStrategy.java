package com.sumanth.strategy.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.sumanth.constants.Constants.Cuisine;
import com.sumanth.objects.CostTracking;
import com.sumanth.objects.CuisineTracking;
import com.sumanth.objects.Restaurant;
import com.sumanth.objects.User;
import com.sumanth.strategy.RecommendationStrategy;

public class AnyCuisineAnyCostRecommendationStrategy implements RecommendationStrategy{

	@Override
	public List<Restaurant> getRecommendations(User user, List<Restaurant> availableRestaurants,
			CuisineTracking primaryCuisineTracking, CostTracking primaryCostTracking,
			List<Cuisine> secondaryCuisineList, List<Integer> secondaryCostList, int maxRecommendedRestaurants) {
		
		return availableRestaurants.stream()
				.sorted(Comparator.comparing(Restaurant::getRating).reversed())
				.collect(Collectors.toList());
	}

}

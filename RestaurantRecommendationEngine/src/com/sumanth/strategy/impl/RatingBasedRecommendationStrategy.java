package com.sumanth.strategy.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.sumanth.constants.Constants.Cuisine;
import com.sumanth.objects.CostTracking;
import com.sumanth.objects.CuisineTracking;
import com.sumanth.objects.Restaurant;
import com.sumanth.objects.User;
import com.sumanth.strategy.RecommendationStrategy;
import com.sumanth.utils.Utils;

public class RatingBasedRecommendationStrategy implements RecommendationStrategy {

	@Override
	public List<Restaurant> getRecommendations(User user, List<Restaurant> availableRestaurants,
			CuisineTracking primaryCuisineTracking, CostTracking primaryCostTracking,
			List<Cuisine> secondaryCuisineList, List<Integer> secondaryCostList, int maxRecommendedRestaurants) {
		
		List<Restaurant> recommendedRestaurants = new ArrayList<>();
		
		/*
		 * 2. All restaurants of Primary cuisine, primary cost bracket with rating >= 4
		 */
		recommendedRestaurants = availableRestaurants.stream()
				.filter(r -> r.getCuisine() == primaryCuisineTracking.getType() &&
				r.getCostBracket() == primaryCostTracking.getType() &&
				r.getRating() >=4)
				.collect(Collectors.toList());
		
		if(recommendedRestaurants.size() >= maxRecommendedRestaurants) {
			return recommendedRestaurants;
		}
		
		/*
		 * 3. All restaurants of Primary cuisine, secondary cost bracket with rating >= 4.5
		 */
		recommendedRestaurants.addAll(availableRestaurants.stream()
				.filter(r -> r.getCuisine() == primaryCuisineTracking.getType()
						&& secondaryCostList.contains(r.getCostBracket()) && r.getRating() >= 4.5)
				.collect(Collectors.toList()));
		Utils.removeDuplicates(recommendedRestaurants);
		if(recommendedRestaurants.size() >= maxRecommendedRestaurants) {
			return recommendedRestaurants;
		}
		
		/*
		 * 4. All restaurants of secondary cuisine, primary cost bracket with rating >= 4.5
		 */
		recommendedRestaurants.addAll(availableRestaurants.stream()
				.filter(r -> secondaryCuisineList.contains(r.getCuisine())
						&& r.getCostBracket() == primaryCostTracking.getType() && r.getRating() >= 4.5)
				.collect(Collectors.toList()));
		Utils.removeDuplicates(recommendedRestaurants);
		if(recommendedRestaurants.size() >= maxRecommendedRestaurants) {
			return recommendedRestaurants;
		}
		
		/*
		 * 5. Top 4 newly created restaurants by rating
		 */
		recommendedRestaurants.addAll(availableRestaurants.stream()
				.filter(r -> Utils.isOnboardedInTheLast48Hours(r))
				.limit(4)
				.collect(Collectors.toList()));
		Utils.removeDuplicates(recommendedRestaurants);
		if(recommendedRestaurants.size() >= maxRecommendedRestaurants) {
			return recommendedRestaurants;
		}
		
		/*
		 * 6. All restaurants of Primary cuisine, primary cost bracket with rating < 4
		 */
		recommendedRestaurants = availableRestaurants.stream()
				.filter(r -> r.getCuisine() == primaryCuisineTracking.getType() &&
				r.getCostBracket() == primaryCostTracking.getType() &&
				r.getRating() <4)
				.collect(Collectors.toList());
		Utils.removeDuplicates(recommendedRestaurants);
		if(recommendedRestaurants.size() >= maxRecommendedRestaurants) {
			return recommendedRestaurants;
		}
		
		/*
		 * 7. All restaurants of Primary cuisine, secondary cost bracket with rating < 4.5
		 */
		recommendedRestaurants.addAll(availableRestaurants.stream()
				.filter(r -> r.getCuisine() == primaryCuisineTracking.getType()
						&& secondaryCostList.contains(r.getCostBracket()) && r.getRating() < 4.5)
				.collect(Collectors.toList()));
		Utils.removeDuplicates(recommendedRestaurants);
		if(recommendedRestaurants.size() >= maxRecommendedRestaurants) {
			return recommendedRestaurants;
		}
		
		/*
		 * 8. All restaurants of secondary cuisine, primary cost bracket with rating < 4.5
		 */
		recommendedRestaurants.addAll(availableRestaurants.stream()
				.filter(r -> secondaryCuisineList.contains(r.getCuisine())
						&& r.getCostBracket() == primaryCostTracking.getType() && r.getRating() < 4.5)
				.collect(Collectors.toList()));
		Utils.removeDuplicates(recommendedRestaurants);
		
		return recommendedRestaurants;
	}
}

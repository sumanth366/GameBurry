package com.sumanth.main;
import com.sumanth.objects.*;
import com.sumanth.constants.Constants.Cuisine;
import com.sumanth.strategy.impl.AnyCuisineAnyCostRecommendationStrategy;
import com.sumanth.strategy.impl.FeaturedRestaurantRecommendationStrategy;
import com.sumanth.strategy.impl.RatingBasedRecommendationStrategy;
import com.sumanth.strategy.impl.RestaurantRecommendationEngine;
import com.sumanth.utils.Utils;

import java.util.*;

public class RestaurantRecommendation {
	
	public static void main(String[] args) {
		/*
		 * Declaration of required Collections
		 */
		List<Restaurant> recommendedRestaurants = new ArrayList<>();
		List<Restaurant> availableRestaurants = new ArrayList<>();
		List<CuisineTracking> cuisines = new ArrayList<>();
		List<CostTracking> costTracker = new ArrayList<>();
		int maxRecommendedRestaurants = 100;
		
		/*
		 * Initializing above collections with few dummy records
		 */
		availableRestaurants = Utils.initializeRestaurants();
		cuisines = Utils.initializeCuisnes();
		costTracker = Utils.initializeCostTracker();
		
		User user = new User(cuisines, costTracker);
		
		
		/*
		 * sort the users cuisine and cost lists in the descending order to 
		 * fetch primary and secondary cusines and cost brackets
		 */
		Utils.sortUsersCusinesAndCosts(user);
		
		/*
		 * fetching primary and secondary cusines and cost brackets
		 */
		CuisineTracking primaryCuisineTracking = user.getCuisines().get(0);
        CostTracking primaryCostTracking = user.getCostBrackets().get(0);        
    	List<Cuisine> secondaryCuisineList = Utils.getSecondaryCuisineList(user); 
        List<Integer> secondaryCostList = Utils.getSecondaryCostList(user); 
		
        
        
		RestaurantRecommendationEngine recommendationEngine;
		
		/*
		 * 1. Featured restaurants of primary cuisine and primary cost bracket. 
		 * If none, then all featured restaurants of primary cuisine, secondary cost 
		 * and secondary cuisine, primary cost
		 */
		recommendationEngine = new RestaurantRecommendationEngine(new FeaturedRestaurantRecommendationStrategy());
		recommendedRestaurants = recommendationEngine.getRestaurantRecommendations(user, availableRestaurants, primaryCuisineTracking,
				primaryCostTracking, secondaryCuisineList, secondaryCostList, maxRecommendedRestaurants);
		
		if(recommendedRestaurants.size() >= maxRecommendedRestaurants) {
			printRecommendedRestaurants(recommendedRestaurants, maxRecommendedRestaurants);
			return;
		}
		

		/*
		 * Fetching rating based recommended restaurants from order 2 to 8 given in the assignment
		 */
		recommendationEngine = new RestaurantRecommendationEngine(new RatingBasedRecommendationStrategy());
		recommendedRestaurants.addAll(recommendationEngine.getRestaurantRecommendations(user, availableRestaurants, primaryCuisineTracking,
				primaryCostTracking, secondaryCuisineList, secondaryCostList, maxRecommendedRestaurants));
		
		Utils.removeDuplicates(recommendedRestaurants);
		
		if(recommendedRestaurants.size() >= maxRecommendedRestaurants) {
			printRecommendedRestaurants(recommendedRestaurants, maxRecommendedRestaurants);
			return;
		}
		

		/*
		 * 9. All restaurants of any cuisine, any cost bracket
		 */
		recommendationEngine = new RestaurantRecommendationEngine(new AnyCuisineAnyCostRecommendationStrategy());
		recommendedRestaurants.addAll(recommendationEngine.getRestaurantRecommendations(user, availableRestaurants, primaryCuisineTracking,
				primaryCostTracking, secondaryCuisineList, secondaryCostList, maxRecommendedRestaurants));
		
		Utils.removeDuplicates(recommendedRestaurants);
		
		printRecommendedRestaurants(recommendedRestaurants, maxRecommendedRestaurants);
			
	}
	
	public static void printRecommendedRestaurants(List<Restaurant> recommendedRestaurants, int maxRestaurants) {
	    System.out.println("Top " + maxRestaurants + " Recommended Restaurants:");
	    int count = 1;
	    for (Restaurant restaurant : recommendedRestaurants) {
	        if (count > maxRestaurants) {
	            break;
	        }
	        System.out.println(count + ". " + restaurant.getRestaurantId() + " - " + restaurant.getCuisine() + ", Cost: " + restaurant.getCostBracket() + ", Rating: " + restaurant.getRating());
	        count++;
	    }
	}

}

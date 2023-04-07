package com.sumanth.utils;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sumanth.constants.Constants.Cuisine;
import com.sumanth.objects.CostTracking;
import com.sumanth.objects.CuisineTracking;
import com.sumanth.objects.Restaurant;
import com.sumanth.objects.User;

public class Utils {

	public static void removeDuplicates(List<Restaurant> recommendedRestaurants) {
		Set<Restaurant> set = new LinkedHashSet<>(recommendedRestaurants);
		recommendedRestaurants.clear();
		recommendedRestaurants.addAll(set);
	}

	public static void sortUsersCusinesAndCosts(User user) {
		Collections.sort(user.getCuisines(), new Comparator<CuisineTracking>() {
			@Override
			public int compare(CuisineTracking c1, CuisineTracking c2) {
				return c2.getNoOfOrders() - c1.getNoOfOrders();
			}
		});

		Collections.sort(user.getCostBrackets(), new Comparator<CostTracking>() {
			@Override
			public int compare(CostTracking c1, CostTracking c2) {
				return c2.getNoOfOrders() - c1.getNoOfOrders();
			}
		});
	}

	public static List<Cuisine> getSecondaryCuisineList(User user) {
		return user.getCuisines().stream().skip(1).limit(2).map(CuisineTracking::getType).collect(Collectors.toList());
	}

	public static List<Integer> getSecondaryCostList(User user) {
		return user.getCostBrackets().stream().skip(1).limit(2).map(CostTracking::getNoOfOrders).collect(Collectors.toList());
	}

	public static boolean isOnboardedInTheLast48Hours(Restaurant restaurant) {
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.HOUR, -48);
	    Date thresholdDate = cal.getTime();
	    return restaurant.getOnboardedTime().after(thresholdDate);
	}

	public static List<Restaurant> initializeRestaurants() {
		Restaurant restaurant1 = new Restaurant("R001", Cuisine.NORTH_INDIAN, 3, 4.2f, true, new Date());
		Restaurant restaurant2 = new Restaurant("R002", Cuisine.SOUTH_INDIAN, 2, 4.5f, false, new Date());
		Restaurant restaurant3 = new Restaurant("R003", Cuisine.CHINESE, 4, 3.8f, true, new Date());
		Restaurant restaurant4 = new Restaurant("R004", Cuisine.NORTH_INDIAN, 5, 4.9f, true, new Date());
		Restaurant restaurant5 = new Restaurant("R005", Cuisine.SOUTH_INDIAN, 1, 4.1f, false, new Date());

		return Stream.of(restaurant1, restaurant2, restaurant3, restaurant4, restaurant5)
				.collect(Collectors.toList());
	}

	public static List<CuisineTracking> initializeCuisnes() {
		CuisineTracking cuisineTracking1 = new CuisineTracking(Cuisine.NORTH_INDIAN, 50);
		CuisineTracking cuisineTracking2 = new CuisineTracking(Cuisine.SOUTH_INDIAN, 30);
		CuisineTracking cuisineTracking3 = new CuisineTracking(Cuisine.CHINESE, 20);
		CuisineTracking cuisineTracking4 = new CuisineTracking(Cuisine.NORTH_INDIAN, 40);
		CuisineTracking cuisineTracking5 = new CuisineTracking(Cuisine.SOUTH_INDIAN, 10);

		return Stream.of(cuisineTracking1, cuisineTracking2, cuisineTracking3, cuisineTracking4, cuisineTracking5)
				.collect(Collectors.toList());
	}

	public static List<CostTracking> initializeCostTracker() {
		CostTracking costTracking1 = new CostTracking(1, 100);
		CostTracking costTracking2 = new CostTracking(2, 150);
		CostTracking costTracking3 = new CostTracking(3, 200);
		CostTracking costTracking4 = new CostTracking(4, 120);
		CostTracking costTracking5 = new CostTracking(5, 80);

		return Stream.of(costTracking1, costTracking2, costTracking3, costTracking4, costTracking5)
				.collect(Collectors.toList());
	}

}

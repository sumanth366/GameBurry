package com.sumanth.objects;
import com.sumanth.constants.Constants.Cuisine;

public class CuisineTracking {
	private Cuisine type;
    private int noOfOrders;

    public CuisineTracking(Cuisine type, int noOfOrders) {
        this.type = type;
        this.noOfOrders = noOfOrders;
    }

    public Cuisine getType() {
        return type;
    }

    public int getNoOfOrders() {
        return noOfOrders;
    }

	@Override
	public String toString() {
		return "CuisineTracking [type=" + type + ", noOfOrders=" + noOfOrders + "]";
	}
    
    
}

package com.sumanth.objects;

import java.util.*;

public class User {
    private List<CuisineTracking> cuisines;
    private List<CostTracking> costBrackets;

    public User(List<CuisineTracking> cuisines, List<CostTracking> costBrackets) {
        this.cuisines = cuisines;
        this.costBrackets = costBrackets;
    }

    public List<CuisineTracking> getCuisines() {
        return cuisines;
    }

    public List<CostTracking> getCostBrackets() {
        return costBrackets;
    }
}

package org.example;

import java.util.stream.IntStream;
import java.util.OptionalInt;

public class AffordableVacation {
  
  public static String findMinCost(int money, int days, int[] cost) {
    //sums for city 0 and duration between 0 and days;
    int[] sumsOfFirstCities = new int[days+1]; // -> sums[0] = 0;
    for (int duration = 1; duration <= days; duration++){
      sumsOfFirstCities[duration] = sumsOfFirstCities[duration-1] + cost[duration-1];
    }
    int minCost = getMinCost(sumsOfFirstCities[days], days, cost);
    if (minCost <= money)
      return "money: " + minCost;
    for (int duration = days-1; duration > 0; duration--){
      System.out.println(duration);
      if (isAffordable(money, sumsOfFirstCities[duration], duration, cost))
        return "days: " + duration;
    }
    return "days: 0";
  }
   
  private static int getMinCost(int costForFirstCity, int days, int[] cost) {
    int minCost = costForFirstCity;
    int currentCost = costForFirstCity;
    int city = 0;
    while (city + days < cost.length){
      currentCost = currentCost - cost[city] + cost[city+days];
      if (currentCost < minCost)
        minCost = currentCost;
      city++;
    }
    return minCost;
  }
  
  private static boolean isAffordable(int money, int costForFirstCity, int duration, int[] cost)  {
    int currentCost = costForFirstCity;
    boolean affordable = (costForFirstCity <= money);
    int city = 0;
    while (city + duration < cost.length && !affordable){
      currentCost = currentCost - cost[city] + cost[city+duration];
      affordable = (currentCost <= money);
      city++;
    }
    return affordable;
  }
  
}              

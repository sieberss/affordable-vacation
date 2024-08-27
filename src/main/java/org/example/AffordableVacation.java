package org.example;

public class AffordableVacation {

  static int [] sumsOfFirstCities;
  static boolean[] affordable;

  public static String findMinCost(int money, int days, int[] cost) {
    affordable = new boolean[days+1];
    //sums for city 0 and duration between 0 and days;
    sumsOfFirstCities = new int[days+1]; // -> sums[0] = 0;
    for (int duration = 1; duration <= days; duration++){
      sumsOfFirstCities[duration] = sumsOfFirstCities[duration-1] + cost[duration-1];
    }
    int minCost = getMinCost(days, cost);
    if (minCost <= money)
      return "money: " + minCost;
    int affordableDuration = getLongestAffordableDuration(minCost, money, days, cost);
    return "days: " + affordableDuration;
  }

  private static int getMinCost(int days, int[] cost) {
    int currentCost = sumsOfFirstCities[days];
    int minCost = currentCost;
    int city = 0;
    while (city + days < cost.length){
      currentCost = currentCost - cost[city] + cost[city+days];
      if (currentCost < minCost)
        minCost = currentCost;
      city++;
    }
    return minCost;
  }

  private static int getLongestAffordableDuration(int minCost, int money, int days, int[] cost) {
    int attemptedDuration = getNextAttempt(minCost, money, days);
    do {
      int attemptedMinCost = getMinCost(attemptedDuration, cost);
      if (attemptedMinCost <= money){
        for (int duration=0; duration <= attemptedDuration; duration++) {
          affordable[duration] = true;
        }
      };
      attemptedDuration = getNextAttempt(attemptedMinCost, money, attemptedDuration);
    }
    while (!affordable[attemptedDuration] || affordable[attemptedDuration+1]);
    return attemptedDuration;
  }

  private static int getNextAttempt(int oldMinCost, int money, int oldDuration) {
    int guess = oldMinCost / money * oldDuration;
    if (!affordable[guess])
      return guess < oldDuration ? guess : oldDuration-1;
    // if guess is affordable, find next duration which is too expensive
    while (affordable[guess]) {
      guess++;
    }
    System.out.println(guess);
    return guess;
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

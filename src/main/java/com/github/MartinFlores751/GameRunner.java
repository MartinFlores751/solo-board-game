package com.github.MartinFlores751;

/**
 * Wrapper class to run multiple game simulations and extract average values over NUM_SIMULATION_RUNS.
 */
public class GameRunner {
    private static final int NUM_SIMULATION_RUNS = 1000;

    public static void main(String[] args) {
        int rollTotal = 0;
        int propertyPurchaseTotal = 0;
        int indianaAvenueTotal = 0;

        // Game simulation loop
        for (int i = 0; i < NUM_SIMULATION_RUNS; i++) {
            // Create game and run the game
            Game game = new Game();
            game.runGame();

            // Get the total number of roll that game and add to total
            rollTotal += game.getNumRolls();

            // Get player from the finished game and extract the number of purchased properties
            // as well as whether or note Indiana Avenue was purchased that round.
            Player currentPlayer = game.getPlayer();
            propertyPurchaseTotal += currentPlayer.getNumPurchasedProperties();
            if (currentPlayer.hasIndianaAvenue())
                indianaAvenueTotal += 1;
        }

        // Calculate Indiana Avenue percentage
        float indianaAvenuePercent = (indianaAvenueTotal / (float) propertyPurchaseTotal) * 100;

        // Print out the collected stats
        System.out.println("Average number of rolls per turn is: " + rollTotal / (float) NUM_SIMULATION_RUNS);
        System.out.println("Average number of properties purchased per turn is: " + propertyPurchaseTotal / (float) NUM_SIMULATION_RUNS);
        System.out.println("Out of " + propertyPurchaseTotal + " purchases, Indiana Avenue was purchased: " + indianaAvenuePercent + "% of the time");
    }
}

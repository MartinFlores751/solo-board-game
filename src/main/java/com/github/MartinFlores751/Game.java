package com.github.MartinFlores751;

import com.github.MartinFlores751.tiles.Penalty;
import com.github.MartinFlores751.tiles.Property;

import java.util.Random;


/**
 * This class contains the core game loop as well as global variables for the board game itself.
 */
public class Game {
    public static final int PASSED_GO_INCOME = 200;
    public static final String[] BOARD = {
            "Mediterranean Avenue",
            "Community Chest",
            "Baltic Avenue",
            "Income Tax",
            "Reading Railroad",
            "Oriental Avenue",
            "Chance",
            "Vermont Avenue",
            "Connecticut Avenue",
            "Jail Visit",
            "St. Charles Place",
            "Electric Company",
            "States Avenue",
            "Virginia Avenue",
            "Pennsylvania Railroad",
            "St. James Place",
            "Community Chest",
            "Tennessee Avenue",
            "New York Avenue",
            "Free Parking",
            "Kentucky Avenue",
            "Chance",
            "Indiana Avenue",
            "Illinois Avenue",
            "B. & O. Railroad",
            "Atlantic Avenue",
            "Ventnor Avenue",
            "Water Works",
            "Marvin Gardens",
            "Police Bribe",
            "Pacific Avenue",
            "North Carolina Avenue",
            "Community Chest",
            "Pennsylvania Avenue",
            "Short Line",
            "Chance",
            "Park Place",
            "Luxury Tax",
            "Boardwalk",
            "Go"
    };
    private static final int STARTING_CASH = 1500;
    private static final int MAX_ROLLS = 1000;
    private static final Penalty[] PENALTIES = {
            new Penalty("Community Chest", 0),
            new Penalty("Income Tax", 200, 10),
            new Penalty("Chance", 0),
            new Penalty("Jail Visit", 50),
            new Penalty("Community Chest", 0),
            new Penalty("Free Parking", 0),
            new Penalty("Chance", 0),
            new Penalty("Police Bribe", 50),
            new Penalty("Community Chest", 0),
            new Penalty("Chance", 0),
            new Penalty("Luxury Tax", 75),
            new Penalty("Go", 0),
    };

    private static final Property[] PROPERTIES = {
            new Property("Mediterranean Avenue", 60),
            new Property("Baltic Avenue", 60),
            new Property("Reading Railroad", 200),
            new Property("Oriental Avenue", 100),
            new Property("Vermont Avenue", 100),
            new Property("Connecticut Avenue", 120),
            new Property("St. Charles Place", 140),
            new Property("Electric Company", 150),
            new Property("States Avenue", 140),
            new Property("Virginia Avenue", 160),
            new Property("Pennsylvania Railroad", 200),
            new Property("St. James Place", 180),
            new Property("Tennessee Avenue", 180),
            new Property("New York Avenue", 200),
            new Property("Kentucky Avenue", 220),
            new Property("Indiana Avenue", 220),
            new Property("Illinois Avenue", 240),
            new Property("B. & O. Railroad", 200),
            new Property("Atlantic Avenue", 260),
            new Property("Ventnor Avenue", 260),
            new Property("Water Works", 150),
            new Property("Marvin Gardens", 280),
            new Property("Pacific Avenue", 300),
            new Property("North Carolina Avenue", 300),
            new Property("Pennsylvania Avenue", 320),
            new Property("Short Line", 200),
            new Property("Park Place", 350),
            new Property("Boardwalk", 400),
    };

    private final Player player;
    private int numRolls = 0;

    Game() {
        player = new Player(STARTING_CASH);
    }

    /**
     * Runs the core loop for the board game.
     */
    public void runGame() {
        // Create random num generator
        Random dice = new Random();

        // Core game loop
        while (numRolls < MAX_ROLLS && player.getCash() > 0) {
            // Roll 2D6
            int dieOne = dice.nextInt(6) + 1;
            int dieTwo = dice.nextInt(6) + 1;

            // Get the total roll number
            int totalRoll = dieOne + dieTwo;

            // Inc the roll counter
            numRolls++;

            // Move current location to new area
            player.updatePosition(totalRoll);

            // Get current location name
            String locationName = BOARD[player.getCurrentPosition()];

            // Declare property and penalty params
            Property currentProperty;
            Penalty currentPenalty;

            // Check if property
            if ((currentProperty = getProperty(locationName)) != null) {
                // Check if player has purchased the property
                if (!player.hasProperty(currentProperty)) {
                    player.purchaseProperty(currentProperty);
                }
            }
            // else check if penalty
            else if ((currentPenalty = getPenalty(locationName)) != null) {
                player.payPenalty(currentPenalty);
            }
        }
    }

    /**
     * Searches through PROPERTIES array looking for a Property with a matching name.
     *
     * @param propertyName Property name to search for
     * @return the property with the matching name. Returns null if no such property exists
     */
    private Property getProperty(String propertyName) {
        for (Property p : PROPERTIES)
            if (p.getName().equals(propertyName))
                return p;
        return null;
    }

    /**
     * Searches through array looking for a Penalty with a matching name.
     *
     * @param penaltyName Penalty name to search for
     * @return the penalty with the matching name. Returns null if no such penalty exists
     */
    private Penalty getPenalty(String penaltyName) {
        for (Penalty p : PENALTIES)
            if (p.getName().equals(penaltyName))
                return p;
        return null;
    }

    /**
     * @return the total number of rolls for this game
     */
    public int getNumRolls() {
        return numRolls;
    }

    /**
     * @return the Player object of this game
     */
    public Player getPlayer() {
        return player;
    }
}

package com.github.MartinFlores751;

import com.github.MartinFlores751.tiles.BoardSquare;
import com.github.MartinFlores751.tiles.Penalty;
import com.github.MartinFlores751.tiles.Property;
import com.github.MartinFlores751.tiles.SquareType;

import java.util.ArrayList;
import java.util.Random;


/**
 * This class contains the core game loop as well as global variables for the board game itself.
 */
public class Game {
    public static final int PASSED_GO_INCOME = 200;

    public static final ArrayList<BoardSquare> BOARD = new ArrayList<BoardSquare>() {{
        add(new Property("Mediterranean Avenue", 60));
        add(new Penalty("Community Chest", 0));
        add(new Property("Baltic Avenue", 60));
        add(new Penalty("Income Tax", 200, 10));
        add(new Property("Reading Railroad", 200));
        add(new Property("Oriental Avenue", 100));
        add(new Penalty("Chance", 0));
        add(new Property("Vermont Avenue", 100));
        add(new Property("Connecticut Avenue", 120));
        add(new Penalty("Jail Visit", 50));
        add(new Property("St. Charles Place", 140));
        add(new Property("Electric Company", 150));
        add(new Property("States Avenue", 140));
        add(new Property("Virginia Avenue", 160));
        add(new Property("Pennsylvania Railroad", 200));
        add(new Property("St. James Place", 180));
        add(new Penalty("Community Chest", 0));
        add(new Property("Tennessee Avenue", 180));
        add(new Property("New York Avenue", 200));
        add(new Penalty("Free Parking", 0));
        add(new Property("Kentucky Avenue", 220));
        add(new Penalty("Chance", 0));
        add(new Property("Indiana Avenue", 220));
        add(new Property("Illinois Avenue", 240));
        add(new Property("B. & O. Railroad", 200));
        add(new Property("Atlantic Avenue", 260));
        add(new Property("Ventnor Avenue", 260));
        add(new Property("Water Works", 150));
        add(new Property("Marvin Gardens", 280));
        add(new Penalty("Police Bribe", 50));
        add(new Property("Pacific Avenue", 300));
        add(new Property("North Carolina Avenue", 300));
        add(new Penalty("Community Chest", 0));
        add(new Property("Pennsylvania Avenue", 320));
        add(new Property("Short Line", 200));
        add(new Penalty("Chance", 0));
        add(new Property("Park Place", 350));
        add(new Penalty("Luxury Tax", 75));
        add(new Property("Boardwalk", 400));
        add(new Penalty("Go", 0));
    }};

    private static final int STARTING_CASH = 1500;
    private static final int MAX_ROLLS = 1000;

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
            BoardSquare location = BOARD.get(player.getCurrentPosition());

            // Check if property
            if (location.getType() == SquareType.PROPERTY) {
                Property property = (Property) location;
                // Check if player has purchased the property
                if (!player.hasProperty(property)) {
                    player.purchaseProperty(property);
                }
            }
            // else check if penalty
            else if (location.getType() == SquareType.PENALTY) {
                Penalty penalty = (Penalty) location;
                player.payPenalty(penalty);
            }
        }
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

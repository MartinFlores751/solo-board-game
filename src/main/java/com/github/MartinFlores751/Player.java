package com.github.MartinFlores751;

import com.github.MartinFlores751.tiles.Penalty;
import com.github.MartinFlores751.tiles.Property;

import java.util.Vector;

/**
 * Class representing the player in the game.
 */
public class Player {
    private static final int BOARD_LENGTH = Game.BOARD.length - 1;

    private final Vector<Property> purchasedLocations = new Vector<>();

    private int cash;
    private int currentPosition = 0;


    Player(int startingCash) {
        cash = startingCash;
    }

    /**
     * @return the current amount of cash the player has.
     */
    public int getCash() {
        return cash;
    }

    /**
     * @param income the amount of cash to add to the players current wealth.
     */
    public void addCash(int income) {
        if (income > 0)
            cash += income;
    }

    /**
     * @param expense the amount of cash to remove from the players current wealth.
     */
    private void removeCash(int expense) {
        if (expense > 0) {
            cash -= expense;
        }
    }

    /**
     * Checks to see if the player has currently purchased the given Property
     *
     * @param p the property to check if the player has purchased
     * @return true if the player has purchased the given Property
     */
    public boolean hasProperty(Property p) {
        return purchasedLocations.contains(p);
    }

    /**
     * Purchase the given property and add to the player's purchased properties
     *
     * @param p the Property to purchase
     */
    public void purchaseProperty(Property p) {
        // Spend money to purchase property
        removeCash(p.getCost());

        // Add property to owned property list
        purchasedLocations.add(p);
    }

    /**
     * Pays the penalty specified by the given Penalty. If the percentage is a positive value, pay the amount equal to
     * the highest amount by either the flat rate or the percentage.
     *
     * @param p the Penalty the player has to pay for
     */
    public void payPenalty(Penalty p) {
        // Ensure there is a cost to the penalty
        if (p.getCost() > 0) {
            // Check to see if there's a percentage fee
            if (p.getPercent() > -1) {
                // Get percentage cost
                int percentageCost = (getCash() * p.getPercent()) / 100;

                // Check to see if flat rate or percentage would cost more and pay accordingly
                removeCash(Math.max(percentageCost, p.getCost()));
            }
            // No percentage just pay the price
            else {
                removeCash(p.getCost());
            }
        }
    }

    /**
     * Helper function for the simulation.
     *
     * @return true if the player has purchased IndianaAvenue
     */
    public boolean hasIndianaAvenue() {
        for (Property p : purchasedLocations)
            if (p.getName().equals("Indiana Avenue"))
                return true;
        return false;
    }

    /**
     * @return the number of purchased properties
     */
    public int getNumPurchasedProperties() {
        return purchasedLocations.size();
    }

    /**
     * @return the current position in the game board
     */
    public int getCurrentPosition() {
        return currentPosition;
    }

    /**
     * Updates the players position on the game board. Pays PASSED_GO_INCOME if the player lands on or passes GO.
     *
     * @param diceRoll the sum of the dice roll
     */
    public void updatePosition(int diceRoll) {
        // Add the roll
        currentPosition += diceRoll;

        // Check to see if roll is past board end
        if (currentPosition > BOARD_LENGTH) {
            currentPosition -= BOARD_LENGTH;
            addCash(Game.PASSED_GO_INCOME);
        } else if (currentPosition == BOARD_LENGTH)
            addCash(Game.PASSED_GO_INCOME);
    }
}

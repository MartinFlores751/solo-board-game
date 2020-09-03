package com.github.MartinFlores751.tiles;

/**
 * Class Representing Penalties of the board game
 */
public class Penalty implements BoardSquare {
    private final String name;
    private final int cost;
    private final int percent;

    public Penalty(String name, int cost) {
        this(name, cost, -1);
    }

    public Penalty(String name, int cost, int percent) {
        this.name = name;
        this.cost = cost;
        this.percent = percent;
    }

    /**
     * @return the name of the Penalty
     */
    public String getName() {
        return name;
    }

    /**
     * @return the cost of the penalty
     */
    public int getCost() {
        return cost;
    }

    /**
     * @return the type that the class is.
     */
    @Override
    public SquareType getType() {
        return SquareType.PENALTY;
    }

    /**
     * @return the percent penalty to apply to the player's current cash holdings.
     * A value of -1 indicates no percentage penalty should be applied.
     */
    public int getPercent() {
        return percent;
    }
}

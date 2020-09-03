package com.github.MartinFlores751.tiles;

/**
 * Class representing the Property tiles
 */
public class Property implements BoardSquare {
    private final String name;
    private final int cost;

    public Property(String propertyName, int cost) {
        name = propertyName;
        this.cost = cost;
    }

    /**
     * @return the name of the Property tile
     */
    public String getName() {
        return name;
    }

    /**
     * @return the cost of the Property tile
     */
    public int getCost() {
        return cost;
    }

    /**
     * @return the type that the class is.
     */
    @Override
    public SquareType getType() {
        return SquareType.PROPERTY;
    }
}

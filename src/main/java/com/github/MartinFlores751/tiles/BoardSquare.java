package com.github.MartinFlores751.tiles;

/**
 * Interface for representing a square on the board.
 */
public interface BoardSquare {
    /**
     * @return the name of the BoardSquare
     */
    public String getName();

    /**
     * @return the cost of the BoardSquare
     */
    public int getCost();

    /**
     * @return the type that the class is.
     */
    public SquareType getType();
}

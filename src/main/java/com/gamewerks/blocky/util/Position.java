package com.gamewerks.blocky.util;

/**
 * This class makes the positional coordinates of the block in the game board.
 */
public class Position {
    public int row;
    public int col;
    
    /**
     * 
     * @param row a positive integer that indicates the block's x coordinate on the board
     * @param col a positive integer that indicates the block's y coordinate on the board
     */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    /**
     * 
     * @param row a positive integer that is to be added to the original row value
     * @param col a positive integer that is to be added to the original column value
     * @return new row and col after addition of the input integers
     */
    public Position add(int row, int col) {
        return new Position(this.row + row, this.col + col);
    }
    
    /**
     * Returns a string showing the current row and column values
     */
    public String toString() { return String.format("(row = %d, col = %d)", row, col); } 
}

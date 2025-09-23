package com.gamewerks.blocky.engine;

import java.util.LinkedList;
import java.util.List;

import com.gamewerks.blocky.util.Constants;
import com.gamewerks.blocky.util.Position;

/**
 * This class Board represents the game board of the Blocky game
 */
public class Board {
    private boolean[][] well;
    
    /**
     * Make a new booleaen[][] to store the coordinates of the board
     */
    public Board() {
        well = new boolean[Constants.BOARD_HEIGHT][Constants.BOARD_WIDTH];
    }
    
    /**
     * 
     * @param row x coordinates of the current position of the block
     * @param col y coordinates of the current position of the block
     * @return true if the current position of the block is not out of bounds, false otherwise
     */
    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < well.length && col >= 0 && col < well[0].length;
        //Changed the <= to < doesn't throw out of bounds error and can go right
    }
    
    /**
     * 
     * @param p position of the current block 
     * @return the result of the method collides
     */
    public boolean collides(Piece p) {
        return collides(p.getLayout(), p.getPosition());
    }
    
    /**
     * 
     * @param layout boolean[][] of the current board
     * @param pos Position (x coordinates and y coordinates) of the current block
     * @return true if the current position of the block collides with others, false otherwise
     */
    public boolean collides(boolean[][] layout, Position pos) {
        for (int row = 0; row < layout.length; row++) {
            int wellRow = pos.row - row;
            for (int col = 0; col < layout[row].length; col++) {
                int wellCol = col + pos.col;
                if (layout[row][col]) {
                    if (!isValidPosition(wellRow, wellCol)) {
                        return true;
                    } else if (well[wellRow][wellCol]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * Moves the position of the block if the new position is valid
     * @param p current position of the block
     */
    public void addToWell(Piece p) {
        boolean[][] layout = p.getLayout();
        Position pos = p.getPosition();
        for (int row = 0; row < layout.length; row++) {
            int wellRow = pos.row - row;
            for (int col = 0; col < layout[row].length; col++) {
                int wellCol = pos.col + col;
                if (isValidPosition(wellRow, wellCol) && layout[row][col]) {
                    well[wellRow][wellCol] = true;
                }
            }
        }
    }
    
    /**
     * 
     * @param n the index of the row to remove
     */
    public void deleteRow(int n) {
        for (int row = 0; row < n - 1; row++) {
            for (int col = 0; col < Constants.BOARD_WIDTH; col++) {
                well[row][col] = well[row+1][col];
            }
        }
        for (int col = 0; col < Constants.BOARD_WIDTH; col++) {
            well[n][col] = false;
        }
    }
    
    /**
     * 
     * @param rows indices of the row to delete
     */
    public void deleteRows(List rows) {
        for (int i = 0; i < rows.size(); i++) {
            int row = (Integer) rows.get(i);
            deleteRow(row);
        }
    }
    
    /**
     * 
     * @param row the index of the row to check if it is full
     * @return true if the row is filled, false otherwise
     */
    public boolean isCompletedRow(int row) {
        boolean isCompleted = true;
        for (int col = 0; col < Constants.BOARD_WIDTH; col++) {
            isCompleted = isCompleted && well[row][col];
        }
        return isCompleted;
    }
    
    /**
     * 
     * @return a List of the completed row indices
     */
    public List getCompletedRows() {
        List completedRows = new LinkedList();
        for (int row = 0; row < Constants.BOARD_HEIGHT; row++) {
            if (isCompletedRow(row)) {
                completedRows.add(row); //passing row (not boolean) so that delection
            }
        }
        return completedRows;
    }
    
    /**
     * 
     * @return the 2D boolean array representing the will
     */
    public boolean[][] getWell() { return well; }
}

package com.gamewerks.blocky.engine;

import java.io.IOException;
import java.util.HashMap;

import com.gamewerks.blocky.util.Loader;
import com.gamewerks.blocky.util.Position;

/**
 * This class maintains the pieces (blocks) of the game according to the user input and reshuffles the blocks.
 */
public class Piece {
    private static int currentIndex;
    
    private static HashMap ROTATION_DATA = null;
    
    static {
        try {
            ROTATION_DATA = Loader.loadAllRotationData();
        } catch (IOException ex) {
            System.out.println("Exception occurred loading rotation data");
            System.exit(-1);
        }
    }
    
    private PieceKind kind;
    private int orientation;
    private Position pos;
    
    /**
     * 
     * @param kind a parameter that is chosen randomly
     * @param pos the initial position of the block on the board
     */
    public Piece(PieceKind kind, Position pos) {
        this.kind = getNextPiece();
        orientation = 0;
        this.pos = pos;
    }
    
    /**
     * 
     * @return a set of Position value which consists of row and column
     */
    public Position getPosition() {
        return pos;
    }
    
    /**
     * 
     * @param p changes the current position pos to the new input position p
     */
    public void moveTo(Position p) {
        pos = p;
    }
    
    /**
     * 
     * @return a new position after rotation
     */
    public boolean[][] getLayout() {
        return ((boolean[][][]) ROTATION_DATA.get(kind))[orientation];
    }
    
    public void rotate(boolean dir) {
        if (dir) {
            orientation = (orientation + 1) % 4;
        } else {
            int k = orientation - 1;
            orientation = k < 0 ? 3 : k;
        }
    }

    /**
     * 
     * @return the current piece in the array of PieceKind. Randomly shuffle the elements if runs out.
     */
    public PieceKind getNextPiece(){
        if(currentIndex >= PieceKind.ALL.length){
            PieceKind.shuffle(PieceKind.ALL);
            currentIndex = 0;
        }
        PieceKind returnPiece = PieceKind.ALL[currentIndex++];
        return returnPiece;
    }
}

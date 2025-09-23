package com.gamewerks.blocky.engine;

import java.io.IOException;
import java.util.HashMap;

import com.gamewerks.blocky.util.Loader;
import com.gamewerks.blocky.util.Position;

public class Piece {
    private int currentIndex = 0;
    
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
    
    public Piece(PieceKind kind, Position pos) {
        this.kind = getNextPiece();
        orientation = 1;
        this.pos = pos;
    }
    
    public Position getPosition() {
        return pos;
    }
    
    public void moveTo(Position p) {
        pos = p;
    }
    
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

    //Random block generation

    //return the current piece and reshuffle array if run out of pieces
    public PieceKind getNextPiece(){
        if(currentIndex >= PieceKind.ALL.length){
            PieceKind.shuffle(PieceKind.ALL);
            currentIndex = 0;
        }
        PieceKind returnPiece = PieceKind.ALL[currentIndex++];
        System.out.println(currentIndex);
        return returnPiece;
    }
}

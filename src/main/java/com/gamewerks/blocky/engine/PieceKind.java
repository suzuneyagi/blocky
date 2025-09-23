package com.gamewerks.blocky.engine;

import java.util.Random;

/**
 * All types of the blocks represented in characters
 */
public enum PieceKind {
    I,
    J,
    L,
    O,
    S,
    T,
    Z;
    
    public static final PieceKind[] ALL = { I, J, L, O, S, T, Z };

    /**
     * Shuffles the elements of the array ALL using the Fisher-Yates Shuffle logic
     * @param ALL the array of PieceKind
     */
    public static void shuffle (PieceKind[] ALL){
        Random rand = new Random();
        for (int i = ALL.length -1; i > 0; i--){
            int j = rand.nextInt(i + 1);
            PieceKind temp = ALL[i];
            ALL[i] = ALL[j];
            ALL[j] = temp;
        }
    }
}

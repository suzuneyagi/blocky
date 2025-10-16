package com.gamewerks.blocky.engine;

import java.util.Random;

public enum PieceKind {
    I,
    J,
    L,
    O,
    S,
    T,
    Z;

    public static final PieceKind[] ALL = { I, J, L, O, S, T, Z };

    int current = 0;

    public static void shuffle(PieceKind[] ALL) {
        Random rand = new Random();
        for (int i = ALL.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            PieceKind temp = ALL[i];
            ALL[i] = ALL[j];
            ALL[j] = temp;
        }
    }
}

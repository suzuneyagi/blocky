package com.gamewerks.blocky.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import com.gamewerks.blocky.engine.PieceKind;

/**
 * Rotates the block, reading the user inputs
 */
public class Loader {
    /**
     * 
     * @param in the scanner providing the block rotation data
     * @return a 2D array representing the rotation layout
     */
    private static boolean[][] readRotation(Scanner in) {
        boolean[][] rotation = new boolean[4][4];
        for (int row = 3; row >= 0; row--) {
            String line = in.nextLine();
            for (int col = 0; col < 4; col++) { //we iterate through 5 columns (indexis 0 to 4) while the rotation array only holds 4 values (0 to 3). We change clumn to iterate only through 4 values (indexes 0-3)
                rotation[row][col] = line.charAt(col) == 'x'; //this is the final line from the main function that fails
            }
        }
        return rotation;
    }
    
    /**
     * 
     * @param piece the type of the block stored in PieceKind
     * @return 3D boolean array where the first index selects the rotation status
     * @throws IOException if the data file cannot be read
     */
    public static boolean[][][] loadRotationData(PieceKind piece) throws IOException {
        boolean[][][] data = new boolean[4][][];
        File file = new File(Constants.DATA_PATH, piece.toString() + ".data");
        Scanner in = new Scanner(file);
        for (int i = 0; i < 4; i++) {
            data[i] = readRotation(in);
            if (in.hasNextLine()) {
                in.nextLine();
            }
        }
        in.close();
        return data;
    }
    
    /**
     * 
     * @return a HashMap 
     * @throws IOException if the data file cannot be read
     */
    public static HashMap loadAllRotationData() throws IOException {
        HashMap ret = new HashMap();
        for (int i = 0; i < PieceKind.ALL.length; i++) {
            PieceKind piece = PieceKind.ALL[i];
            ret.put(piece, loadRotationData(piece));
        }
        return ret;
    }
}

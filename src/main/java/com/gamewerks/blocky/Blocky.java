package com.gamewerks.blocky;

import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import com.gamewerks.blocky.engine.BlockyGame;
import com.gamewerks.blocky.engine.Direction;
import com.gamewerks.blocky.gfx.BlockyPanel;

/**
 * This class initializes the main game window, sets up the game board, and takes the user inputs
 */
public class Blocky {
    private static final int FPS = 10;
    private static final double SPF = 1000000000.0 / FPS;
    /**
     * 
     * @param args user input
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Blocky");
        
        BlockyGame game = new BlockyGame();
        BlockyPanel panel = new BlockyPanel(game);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        
        /**
         * Read user inputs from keyboard, change directions and rotate the blocks accordingly
         */
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                if (code == e.VK_LEFT) {
                    game.setDirection(Direction.LEFT);
                } else if (code == e.VK_RIGHT) {
                    game.setDirection(Direction.RIGHT);
                }
            }
            
            public void keyReleased(KeyEvent e) {
                int code = e.getKeyCode();
                if (code == e.VK_LEFT || code == e.VK_RIGHT) {
                    game.setDirection(Direction.NONE);
                } else if (code == e.VK_Z) {
                    game.rotatePiece(false);
                } else if (code == e.VK_X) {
                    game.rotatePiece(true);
                }
            }
        });
        
        long timeElapsed = 0;
        long prevTime = System.nanoTime();

        /**
         * Animate the game
         */
        while (true) {
            long currentTime = System.nanoTime();
            timeElapsed += currentTime - prevTime;
            prevTime = currentTime;
            if (timeElapsed > SPF) {
                game.step();
                panel.paintImmediately(new Rectangle(0, 0, panel.getWidth(), panel.getHeight()));
                timeElapsed = (long) (timeElapsed - SPF);
            }
        }
    }
}

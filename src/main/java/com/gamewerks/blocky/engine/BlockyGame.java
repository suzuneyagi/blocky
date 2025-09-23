package com.gamewerks.blocky.engine;

import com.gamewerks.blocky.util.Constants;
import com.gamewerks.blocky.util.Position;

/**
 * Set up the game board and execute the block-movement functions
 */
public class BlockyGame {
    private static final int LOCK_DELAY_LIMIT = 30;
    
    private Board board;
    private Piece activePiece;
    private Direction movement;
    
    private int lockCounter;
    
    /**
     * Initialization of the board
     */
    public BlockyGame() {
        board = new Board();
        movement = Direction.NONE;
        lockCounter = 0;
        trySpawnBlock();
    }
    
    /**
     * Initialize the block positions and where it is spawned
     */
    private void trySpawnBlock() {
        if (activePiece == null) {
            //Here we initialize the hight by subtracting 20, so that the block spawns at the top
            activePiece = new Piece(PieceKind.I, new Position(Constants.BOARD_HEIGHT - 1, Constants.BOARD_WIDTH / 2 - 2));
            if (board.collides(activePiece)) {
                System.exit(0);
            }
        }
    }

    /**
     * Read the movement input and executes accordingly
     */
    private void processMovement() {
        Position nextPos;
        //When we launched the game, we couldn't move, so we decided to look at this method
        switch(movement) { //movement can be NONE, LEFT, RIGHT
        case NONE:
            nextPos = activePiece.getPosition();
            break;
        case LEFT:
            nextPos = activePiece.getPosition().add(0, -1);
            break;
        case RIGHT:
            nextPos = activePiece.getPosition().add(0, 1);
            break; //added break here
        default:
            throw new IllegalStateException("Unrecognized direction: " + movement.name());
        }
        if (!board.collides(activePiece.getLayout(), nextPos)) {
            activePiece.moveTo(nextPos);
        }
    }
    
    /**
     * Process the down to bottom movements of the block, checking the validity of the movement
     */
    private void processGravity() {
        Position nextPos = activePiece.getPosition().add(-1, 0);
        if (!board.collides(activePiece.getLayout(), nextPos)) {
            lockCounter = 0;
            activePiece.moveTo(nextPos);
        } else {
            if (lockCounter < LOCK_DELAY_LIMIT) {
                lockCounter += 1;
            } else {
                board.addToWell(activePiece);
                lockCounter = 0;
                activePiece = null;
            }
        }
    }
    
    /**
     * Delete the row on the board if it is filled
     */
    private void processClearedLines() {
        board.deleteRows(board.getCompletedRows());
    }
    
    /**
     * Execute the steps to move the blocks
     */
    public void step() {
        //We saw that processMovement() was highlighting yellow, which means it wasn't used
        //To allow the piece to move left and right, we added the processMovement() call here.
        //It seems that step contains and calls the major functions we need to play the game 
        trySpawnBlock();
        processMovement();
        processGravity();
        processClearedLines();
    }
    
    /**
     * 
     * @return 2D boolean array
     */
    public boolean[][] getWell() {
        return board.getWell();
    }
    
    public Piece getActivePiece() { return activePiece; }
    public void setDirection(Direction movement) { this.movement = movement; }
    public void rotatePiece(boolean dir) { activePiece.rotate(dir); }
}

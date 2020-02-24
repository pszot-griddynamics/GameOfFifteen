package com.griddynamics.gameoffifteen;

import com.griddynamics.gameoffifteen.enums.Direction;
import com.griddynamics.gameoffifteen.move.AbstractTileMove;
import com.griddynamics.gameoffifteen.move.TileMove;
import com.griddynamics.gameoffifteen.move.analyse.MoveAnalyser;
import com.griddynamics.gameoffifteen.utils.ArrayUtils;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Board {
    public static final int[] SOLVED_BOARD = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};

    private int[] beginningState;
    private int[] matrix = new int[SOLVED_BOARD.length];
    private final Random random;
    private final MoveAnalyser analyser;
    private int emptyTileIndex;

    public Board(final Random random, final MoveAnalyser analyser) {

        this.random = random;
        this.analyser = analyser;
        fillBoard();
    }

    /**
     * Fill board with shuffled integers in range from 0 to 15.
     */
    public void fillBoard() {
        List<Integer> fifteen = ArrayUtils.intArrayToList(SOLVED_BOARD);
        Collections.shuffle(fifteen, this.random);
        this.emptyTileIndex = fifteen.indexOf(0);
        this.matrix = fifteen.stream().mapToInt(i -> i).toArray();
        this.beginningState = matrix.clone();
    }

    /**
     * Perform a move of the empty tile in given direction.
     *
     * @param direction Direction of the move
     * @return True if move has been performed or false if not
     */
    public boolean move(final Direction direction) {
        AbstractTileMove move = new TileMove(direction, this.emptyTileIndex, this.matrix);

        if (analyser.canMove(move)) {
            move.process();
            analyser.analyse(move);
            this.matrix = move.getBoard();
            this.emptyTileIndex += direction.getIndexMod();

            return true;
        }

        return false;
    }

    /**
     * @return Beginning state of this board
     */
    public int[] getBeginningState() {
        return beginningState;
    }

    /**
     * Set the beginning state of this board.
     *
     * @param beginningState New beginning state
     */
    public void setBeginningState(final int[] beginningState) {
        this.beginningState = beginningState;
    }

    /**
     * @return Array representation of board
     */
    public int[] getMatrix() {
        return matrix;
    }

    /**
     * Set new matrix and find empty tile inside it.
     *
     * @param matrix New matrix
     */
    public void setMatrix(final int[] matrix) {
        this.matrix = matrix;
        this.emptyTileIndex = indexOfEmptyTile();
        if (this.emptyTileIndex == -1) throw new IllegalStateException("Board does not contains empty tile!");
    }

    /**
     * @return Random used for shuffling this board
     */
    public Random getRandom() {
        return random;
    }

    /**
     * @return Analyser with registered moves that have been performed
     */
    public MoveAnalyser getAnalyser() {
        return analyser;
    }

    /**
     * @return Index of empty tile (0) in this board
     */
    public int getEmptyTileIndex() {
        return emptyTileIndex;
    }


    /**
     * @return Index of empty tile inside the board or -1 if empty tile does not exists
     */
    private int indexOfEmptyTile() {
        for (int i = 0; i < this.matrix.length; i++) {
            if (matrix[i] == 0) return i;
        }

        return -1;
    }

}

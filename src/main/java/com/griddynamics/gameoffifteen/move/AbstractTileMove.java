package com.griddynamics.gameoffifteen.move;

import com.griddynamics.gameoffifteen.enums.Direction;
import com.griddynamics.gameoffifteen.move.interfaces.Instruction;

import java.util.Arrays;

public abstract class AbstractTileMove implements Instruction, Cloneable {

    protected final Direction direction;
    protected final int from;
    protected int[] board;

    public AbstractTileMove(final Direction direction, final int from, final int[] board) {

        this.direction = direction;
        this.from = from;
        this.board = Arrays.copyOf(board, board.length);

    }

    /**
     * Swap tile with place inside {@code board}.
     */
    protected final void swapTiles() {
        int to = getTo();

        int tile = board[from];
        int secondTile = board[to];

        board[from] = secondTile;
        board[to] = tile;
    }

    /**
     * @return Direction of the move
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * @return Index of tile from which move will be performed
     */
    public int getFrom() {
        return from;
    }

    /**
     * @return Index of tile after move
     */
    public int getTo() {
        return from + direction.getIndexMod();
    }

    /**
     * @return Board on which move is performed
     */
    public int[] getBoard() {
        return board;
    }

    /**
     * @return Clone of the current move
     */
    @Override
    public AbstractTileMove clone() {
        try {
            final AbstractTileMove clone = (AbstractTileMove) super.clone();
            clone.board = board.clone();

            return clone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return null;
    }
}

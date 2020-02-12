package com.griddynamics.gameoffifteen.move;

import com.griddynamics.gameoffifteen.enums.Direction;
import com.griddynamics.gameoffifteen.move.interfaces.Move;

import java.util.Arrays;

public abstract class AbstractTileMove implements Move {

    protected final Direction direction;
    protected int from;
    protected int[] board;

    public AbstractTileMove(final Direction direction, final int from, final int[] board) {

        this.direction = direction;
        this.from = from;
        this.board = Arrays.copyOf(board, board.length);

    }

    /**
     * Swap tile with place inside {@code board}
     */
    protected final void swapTiles() {
        int to = getTo();

        int tile = board[from];
        int secondTile = board[to];

        board[from] = secondTile;
        board[to] = tile;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return from + direction.getIndexMod();
    }

    public int[] getBoard() {
        return board;
    }

    @Override
    public AbstractTileMove clone() {

        try {

            final AbstractTileMove clone = (AbstractTileMove) super.clone();
            clone.board = Arrays.copyOf(board, board.length);

            return clone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return null;

    }
}

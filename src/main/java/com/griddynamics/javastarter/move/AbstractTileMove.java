package com.griddynamics.javastarter.move;

import com.griddynamics.javastarter.enums.Direction;
import com.griddynamics.javastarter.move.interfaces.Move;

import java.util.Arrays;

public abstract class AbstractTileMove implements Move {

    protected final Direction direction;
    protected int from;
    protected final int[] board;

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

}

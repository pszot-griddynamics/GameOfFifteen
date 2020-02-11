package com.griddynamics.javastarter.move;

import com.griddynamics.javastarter.enums.Direction;

public class TileMove extends AbstractTileMove {

    public TileMove(final Direction direction, final int from, final int[] board) {
        super(direction, from, board);
    }

    @Override
    public void process() {
        swapTiles();
    }

    @Override
    public void revert() {
        swapTiles();
    }

}

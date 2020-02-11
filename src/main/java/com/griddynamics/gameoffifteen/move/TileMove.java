package com.griddynamics.gameoffifteen.move;

import com.griddynamics.gameoffifteen.enums.Direction;

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

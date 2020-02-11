package com.griddynamics.javastarter.move;

import com.griddynamics.javastarter.enums.Direction;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class RandomTileMove extends AbstractTileMove {

    public RandomTileMove(@NotNull final int[] board, final int from, @NotNull final Random random) {
        super(Direction.values()[random.nextInt(4)], from, board);
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

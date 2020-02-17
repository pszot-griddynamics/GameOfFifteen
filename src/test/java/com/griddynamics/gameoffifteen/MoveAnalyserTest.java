package com.griddynamics.gameoffifteen;

import com.griddynamics.gameoffifteen.enums.Direction;
import com.griddynamics.gameoffifteen.move.AbstractTileMove;
import com.griddynamics.gameoffifteen.move.TileMove;
import com.griddynamics.gameoffifteen.move.analyse.MoveAnalyser;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import static org.junit.Assert.*;

public class MoveAnalyserTest {
    private final int[] TILE_AT_CENTER_MATRIX = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 10, 11, 12, 13, 15, 14};
    private final int[] ALMOST_COMPLETE_MATRIX = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 0, 13, 14, 15, 12};

    @Test
    public void shouldReturnCopyOfMoves() {
        final MoveAnalyser moveAnalyser = createAnalyserWithRandomMove();

        final TileMove move = new TileMove(Direction.getRandomDirection(), 9, TILE_AT_CENTER_MATRIX);

        move.process();
        moveAnalyser.analyse(move);

        assertNotSame(moveAnalyser.getCopyOfMoves().get(1), move);
    }

    @Test
    public void shouldBeCompleteWhenCorrectIndexes() {
        MoveAnalyser analyser = new MoveAnalyser();
        AbstractTileMove move = new TileMove(Direction.DOWN, 11, ALMOST_COMPLETE_MATRIX);
        move.process();
        assertTrue(analyser.analyse(move));
    }

    @Test
    public void shouldNotBeCompleteWhenIncorrectIndexes() {
        MoveAnalyser analyser = new MoveAnalyser();
        AbstractTileMove move = new TileMove(Direction.UP, 11, ALMOST_COMPLETE_MATRIX);
        move.process();
        assertFalse(analyser.analyse(move));
    }

    @NotNull
    private MoveAnalyser createAnalyserWithRandomMove() {
        final MoveAnalyser moveAnalyser = new MoveAnalyser();

        final TileMove move = new TileMove(Direction.getRandomDirection(), 9, TILE_AT_CENTER_MATRIX);

        move.process();
        moveAnalyser.analyse(move);

        return moveAnalyser;
    }
}



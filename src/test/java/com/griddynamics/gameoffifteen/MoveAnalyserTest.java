package com.griddynamics.gameoffifteen;

import com.griddynamics.gameoffifteen.move.RandomTileMove;
import com.griddynamics.gameoffifteen.move.analyse.MoveAnalyser;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotSame;

public class MoveAnalyserTest {
    private final int[] TEST_MATRIX = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 10, 11, 12, 13, 15, 14};

    @Test
    public void shouldReturnCopyOfMoves() {
        final MoveAnalyser moveAnalyser = createAnalyserWithMove();

        final RandomTileMove move = new RandomTileMove(TEST_MATRIX, 9, ThreadLocalRandom.current());

        moveAnalyser.move(move);

        assertNotSame(moveAnalyser.getCopyOfMoves().get(1), move);
    }

    @Test
    public void shouldReturnBeginningState() {

        assertArrayEquals(createAnalyserWithMove().getBeginningState(), TEST_MATRIX);

    }

    @NotNull
    private MoveAnalyser createAnalyserWithMove(){
        final MoveAnalyser moveAnalyser = new MoveAnalyser();

        final RandomTileMove move = new RandomTileMove(TEST_MATRIX, 9, ThreadLocalRandom.current());

        moveAnalyser.move(move);

        return moveAnalyser;
    }
}



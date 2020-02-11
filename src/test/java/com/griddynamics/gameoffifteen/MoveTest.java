package com.griddynamics.gameoffifteen;

import com.griddynamics.gameoffifteen.enums.Direction;
import com.griddynamics.gameoffifteen.move.TileMove;
import com.griddynamics.gameoffifteen.move.analyse.MoveAnalyser;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

public class MoveTest {
    private final int[] RANDOM_MATRIX = {1, 2, 3, 0, 5, 6, 7, 4, 9, 10, 11, 8, 13, 14, 15, 12};

    private final int[] SOLVED_MATRIX = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};


    @Test
    public void shouldMoveEmptyTile() {
        int[] newMatrix = moveDown(RANDOM_MATRIX, 3);
        assertEquals(Arrays.toString(new int[]{1, 2, 3, 4, 5, 6, 7, 0, 9, 10, 11, 8, 13, 14, 15, 12}), Arrays.toString(newMatrix));
    }

    @Test
    public void shouldNotMoveEmptyTileToTheEdge() {
        MoveAnalyser analyser = new MoveAnalyser();
        assertFalse(analyser.canMove(RANDOM_MATRIX, 3, Direction.RIGHT));
    }

    @Test
    public void shouldPerformRandomMove() {
        final int[] RANDOM_MATRIX = {1, 2, 3, 4, 5, 0, 7, 6, 9, 10, 11, 8, 13, 14, 15, 12};

        MoveAnalyser analyser = new MoveAnalyser();

        int[] matrix = analyser.randomMove(RANDOM_MATRIX, 5, new Random());

        MatcherAssert.assertThat(Arrays.toString(RANDOM_MATRIX), not(equalTo(Arrays.toString(matrix))));
    }

    @Test
    public void shouldMoveEmptyTileTwice() {
        int[] board = moveDown(RANDOM_MATRIX, 3);

        board = moveDown(board, 7);

        assertEquals(Arrays.toString(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 0, 13, 14, 15, 12}), Arrays.toString(board));
    }

    @Test
    public void shouldMoveEmptyTileTrice() {

        int[] board = moveDown(RANDOM_MATRIX, 3);

        board = moveDown(board, 7);

        board = moveDown(board, 11);

        assertEquals(Arrays.toString(SOLVED_MATRIX), Arrays.toString(board));
    }

    @Test
    public void shouldBeCompleteWhenCorrectIndexes() {
        MoveAnalyser analyser = new MoveAnalyser();

        assertTrue(analyser.isComplete(SOLVED_MATRIX));
    }

    @Test
    public void shouldNotBeCompleteWhenIncorrectIndexes() {
        MoveAnalyser analyser = new MoveAnalyser();

        assertFalse(analyser.isComplete(RANDOM_MATRIX));
    }

    private int[] moveDown(int[] matrix, int from) {
        TileMove move = new TileMove(Direction.DOWN, from, matrix);
        move.process();
        return move.getBoard();
    }
}

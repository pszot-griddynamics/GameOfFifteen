package com.griddynamics.javastarter;

import com.griddynamics.javastarter.move.analyse.MoveAnalyser;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class BoardTest {
    private final int[] SORTED_MATRIX = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
    private final int[] SOLVED_MATRIX = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};

    private Board board;

    @Before
    public void resetBoard() {
        MoveAnalyser analyser = new MoveAnalyser();
        Random random = new Random();

        board = new Board(random, analyser);
    }

    @Test
    public void shouldFillMatrix() {

        int[] matrix = board.getMatrix();
        Arrays.sort(matrix);
        assertEquals(Arrays.toString(matrix), Arrays.toString(SORTED_MATRIX));

    }

    @Test
    public void shouldFindIndexOfEmptyTile() {

        board.setMatrix(SOLVED_MATRIX);
        assertThat(board.getEmptyTileIndex(), equalTo(15));

    }
}

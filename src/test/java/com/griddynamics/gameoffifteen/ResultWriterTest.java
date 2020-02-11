package com.griddynamics.gameoffifteen;

import com.griddynamics.gameoffifteen.enums.Direction;
import com.griddynamics.gameoffifteen.move.TileMove;
import com.griddynamics.gameoffifteen.move.analyse.MoveAnalyser;
import com.griddynamics.gameoffifteen.result.FileResultWriter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

public class ResultWriterTest {
    private File out = new File("src/main/java/out.txt");
    private Board board = new Board(new Random(), new MoveAnalyser());
    private final int[] RANDOM_MATRIX = {1, 2, 3, 0, 5, 6, 7, 4, 9, 10, 11, 8, 13, 14, 15, 12};


    @After
    public void deleteOut() {
        out.delete();
    }

    @NotNull
    @Contract(" -> new")
    private FileResultWriter createNewWriter() {
        try {
            return new FileResultWriter(new BufferedWriter(new FileWriter(out)), board);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @NotNull
    @Contract(" -> new")
    private BufferedReader createNewReader() {
        try {
            return new BufferedReader(new FileReader(out));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Test
    public void shouldCreateNewFile() {
        final FileResultWriter writer = createNewWriter();
        writer.close();
        assertTrue(out.exists());
    }

    @Test
    public void shouldWriteBoard() throws IOException {

        try (FileResultWriter newWriter = createNewWriter()) {
            newWriter.writeBoard(RANDOM_MATRIX);
        }

        try (BufferedReader reader = createNewReader()) {

            final List<String> collect = reader.lines().collect(Collectors.toList());
            assertTrue(collect.containsAll(Arrays.asList("[ 1  2  3  0]", "[ 5  6  7  4]", "[ 9 10 11  8]", "[13 14 15 12]")));

        }
    }

    @Test
    public void shouldWriteMove() throws IOException {

        try (FileResultWriter newWriter = createNewWriter()) {
            TileMove tileMove = new TileMove(Direction.DOWN, 3, RANDOM_MATRIX);
            newWriter.writeBoard(tileMove);
        }

        try (BufferedReader reader = createNewReader()) {

            final List<String> collect = reader.lines().collect(Collectors.toList());
            assertTrue(collect.containsAll(Arrays.asList("DOWN", "[ 1  2  3  0]", "[ 5  6  7  4]", "[ 9 10 11  8]", "[13 14 15 12]")));

        }
    }

    @Test
    public void shouldWriteAnalyser() throws IOException {

        try (FileResultWriter newWriter = createNewWriter()) {
            board.setMatrix(RANDOM_MATRIX);
            board.move(Direction.DOWN);
            newWriter.writeAnalyser();
        }

        try (BufferedReader reader = createNewReader()) {

            final List<String> collect = reader.lines().collect(Collectors.toList());
            assertTrue(collect.containsAll(Arrays.asList("[ 1  2  3  0]", "Puzzle has been solved in 1 moves", "DOWN", "[ 1  2  3  4]", "[ 5  6  7  0]", "[ 9 10 11  8]", "[13 14 15 12]")));

        }
    }

}

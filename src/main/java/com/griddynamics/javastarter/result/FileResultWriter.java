package com.griddynamics.javastarter.result;

import com.griddynamics.javastarter.Board;
import com.griddynamics.javastarter.move.analyse.MoveAnalyser;
import com.griddynamics.javastarter.move.interfaces.Move;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileResultWriter implements ResultWriter {
    private final BufferedWriter out;
    private final Board board;
    private final String matrixFormat =
            "[%2s %2s %2s %2s]\n"
                    + "[%2s %2s %2s %2s]\n"
                    + "[%2s %2s %2s %2s]\n"
                    + "[%2s %2s %2s %2s]";

    public FileResultWriter(final BufferedWriter out, final Board board) {

        this.out = out;
        this.board = board;

    }

    /**
     * Write board to the file as matrix
     * example:
     * [ 0  1  2  3]
     * [ 3  5  6  7]
     * [ 8  9 10 11]
     * [12 13 14 15]
     *
     * @param matrix Matrix to be written
     */
    public void writeBoard(final int[] matrix) {

        String[] formatArgs = Arrays.stream(matrix).mapToObj(Integer::toString).collect(Collectors.toList()).toArray(new String[0]);
        String formattedMatrix = String.format(matrixFormat, formatArgs);

        newLine();
        for (String line : formattedMatrix.split("\n")) {
            write(line);
            newLine();
        }

    }

    /**
     * Writes to the file board with the direction of performed move
     * example:
     * <p>
     * RIGHT
     * [ 0  1  2  3]
     * [ 3  5  6  7]
     * [ 8  9 10 11]
     * [12 13 14 15]
     *
     * @param move Move to be written
     */
    public void writeBoard(@NotNull final Move move) {

        newLine();
        write(move.getDirection().toString());
        writeBoard(move.getBoard());
        newLine();

    }

    /**
     * Write number of moves performed inside analyser along with these moves
     * Also writes a starting state of board by reverting first move and writes it at the beginning of the file
     */
    public void writeAnalyser() {
        MoveAnalyser analyser = board.getAnalyser();

        final List<Move> moves = analyser.getMoves();
        if (moves.size() > 0) {
            final Move move = moves.get(0);
            move.revert();
            writeBoard(move.getBoard());
            newLine();
            move.process();
        }

        write("Puzzle has been solved in " + moves.size() + " moves");
        newLine();
        moves.forEach(this::writeBoard);

    }

    @Override
    public void newLine() {
        try {
            out.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(final String text) {
        try {
            out.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getMatrixFormat() {
        return this.matrixFormat;
    }

    public BufferedWriter getOut() {
        return out;
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public void close() {
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.griddynamics.gameoffifteen.result;

import com.griddynamics.gameoffifteen.Board;
import com.griddynamics.gameoffifteen.move.AbstractTileMove;
import com.griddynamics.gameoffifteen.move.analyse.MoveAnalyser;
import com.griddynamics.gameoffifteen.utils.ArrayUtils;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public final class FileResultWriter implements ResultWriter {
    private final BufferedWriter out;
    private final Board board;

    public FileResultWriter(final File out, final Board board) {

        try {
            this.out = new BufferedWriter(new FileWriter(out));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        this.board = board;

    }

    /**
     * Write board to the file as matrix.
     * example:
     * [ 0  1  2  3]
     * [ 3  5  6  7]
     * [ 8  9 10 11]
     * [12 13 14 15]
     *
     * @param matrix Matrix to be written
     */
    public void writeBoard(final int[] matrix) {
        String formattedMatrix = ArrayUtils.arrayToMatrixString(matrix);

        newLine();
        for (String line : formattedMatrix.split("\n")) {
            write(line);
            newLine();
        }
    }

    /**
     * Writes to the file board with the direction of performed move.
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
    public void writeBoard(@NotNull final AbstractTileMove move) {

        newLine();
        write(move.getDirection().toString());
        writeBoard(move.getBoard());
        newLine();

    }

    /**
     * Write number of moves performed inside analyser along with these moves.
     * Also writes a starting state of board by reverting first move and writes it at the beginning of the file.
     */
    public void writeAnalyser() {
        MoveAnalyser analyser = board.getAnalyser();

        final List<AbstractTileMove> moves = analyser.getCopyOfMoves();

        writeBoard(board.getBeginningState());
        newLine();

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

    /**
     * @return File output stream as Buffered writer
     */
    public BufferedWriter getOut() {
        return out;
    }

    @Override
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

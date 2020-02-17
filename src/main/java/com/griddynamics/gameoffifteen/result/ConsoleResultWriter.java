package com.griddynamics.gameoffifteen.result;

import com.griddynamics.gameoffifteen.Board;
import com.griddynamics.gameoffifteen.move.AbstractTileMove;
import com.griddynamics.gameoffifteen.move.analyse.MoveAnalyser;
import com.griddynamics.gameoffifteen.utils.ArrayUtils;
import org.jetbrains.annotations.NotNull;

import java.io.PrintStream;

public final class ConsoleResultWriter implements ResultWriter {
    private final PrintStream out;
    private final Board board;

    public ConsoleResultWriter(final PrintStream out, final Board board) {

        this.out = out;
        this.board = board;

    }

    /**
     * Write out the board to the console as matrix.
     * example:
     * [ 0  1  2  3]
     * [ 3  5  6  7]
     * [ 8  9 10 11]
     * [12 13 14 15]
     *
     * @param matrix Matrix to be written
     */
    public void writeBoard(final int[] matrix) {
        write("\n" + ArrayUtils.arrayToMatrixString(matrix) + "\n");
    }

    /**
     * Writes to the console board with the direction of performed move.
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
        write(move.getDirection().toString());
        writeBoard(move.getBoard());
    }

    /**
     * Write number of moves performed inside analyser along with these moves.
     */
    public void writeAnalyser() {
        MoveAnalyser analyser = board.getAnalyser();
        write("Puzzle has been solved in " + analyser.getCopyOfMoves().size() + " moves\n");
        analyser.getCopyOfMoves().forEach(this::writeBoard);
    }

    @Override
    public void newLine() {
        out.println("\n");
    }

    @Override
    public void write(final String text) {
        out.println(text);
    }

    /**
     * @return Console output stream
     */
    public PrintStream getOut() {
        return out;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public void close() {
        out.close();
    }
}

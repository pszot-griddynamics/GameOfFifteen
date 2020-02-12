package com.griddynamics.gameoffifteen.result;

import com.griddynamics.gameoffifteen.Board;
import com.griddynamics.gameoffifteen.move.analyse.MoveAnalyser;
import com.griddynamics.gameoffifteen.move.interfaces.Move;
import org.jetbrains.annotations.NotNull;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ConsoleResultWriter implements ResultWriter {
    private final PrintStream out;
    private final Board board;
    private final String matrixFormat =
            "[%2s %2s %2s %2s]\n"
                    + "[%2s %2s %2s %2s]\n"
                    + "[%2s %2s %2s %2s]\n"
                    + "[%2s %2s %2s %2s]";

    public ConsoleResultWriter(final PrintStream out, final Board board) {

        this.out = out;
        this.board = board;

    }

    /**
     * Write out the board to the console as matrix
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
        write("\n" + String.format(matrixFormat, formatArgs) + "\n");
    }

    /**
     * Writes to the console board with the direction of performed move
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

        write(move.getDirection().toString());
        writeBoard(move.getBoard());

    }

    /**
     * Write number of moves performed inside analyser along with these moves
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

    @Override
    public String getMatrixFormat() {
        return this.matrixFormat;
    }

    public PrintStream getOut() {
        return out;
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public void close() {
        out.close();
    }
}

package com.griddynamics.gameoffifteen.result;

import com.griddynamics.gameoffifteen.Board;
import com.griddynamics.gameoffifteen.move.AbstractTileMove;

public interface ResultWriter extends AutoCloseable {
    /**
     * Writes given array to an {@code java.io.OutputStream} specified in concrete implementation.
     *
     * @param matrix Array to be written out
     */
    void writeBoard(int[] matrix);

    /**
     * Writes given move to an {@code java.io.OutputStream} specified in concrete implementation.
     * Should write direction of the move and state of the inner matrix
     *
     * @param move Move to be written out
     */
    void writeBoard(AbstractTileMove move);

    /**
     * Writes a new line depending on the OS
     */
    void newLine();

    /**
     * Writes text to an {@code java.io.OutputStream} specified in concrete implementation.
     *
     * @param text Text to be written
     */
    void write(String text);

    /**
     * @return Board instance bound to this Result Writer
     */
    Board getBoard();
}

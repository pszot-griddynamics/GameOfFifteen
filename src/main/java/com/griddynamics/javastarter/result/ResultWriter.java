package com.griddynamics.javastarter.result;

import com.griddynamics.javastarter.Board;
import com.griddynamics.javastarter.move.interfaces.Move;

public interface ResultWriter extends AutoCloseable {
    void writeBoard(int[] matrix);

    void writeBoard(Move move);

    void newLine();

    void write(String text);

    String getMatrixFormat();

    Board getBoard();
}

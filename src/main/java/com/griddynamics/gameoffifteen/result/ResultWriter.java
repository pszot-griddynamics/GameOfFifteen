package com.griddynamics.gameoffifteen.result;

import com.griddynamics.gameoffifteen.Board;
import com.griddynamics.gameoffifteen.move.interfaces.Move;

public interface ResultWriter extends AutoCloseable {
    void writeBoard(int[] matrix);

    void writeBoard(Move move);

    void newLine();

    void write(String text);

    String getMatrixFormat();

    Board getBoard();
}

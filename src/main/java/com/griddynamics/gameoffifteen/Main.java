package com.griddynamics.gameoffifteen;

import com.griddynamics.gameoffifteen.enums.Direction;
import com.griddynamics.gameoffifteen.move.analyse.MoveAnalyser;
import com.griddynamics.gameoffifteen.result.ConsoleResultWriter;
import com.griddynamics.gameoffifteen.result.FileResultWriter;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Random;
import java.util.Scanner;

public final class Main {
    private static final File OUTPUT_FILE = new File("src/main/java/out.txt");

    public static void main(@NotNull final String[] args) {
        MoveAnalyser analyser = new MoveAnalyser();
        Board board = new Board(new Random(), analyser);

        // Example matrix just to show how it works with a simple case (Just uncomment if you want to see)
        //board.setMatrix(new int[]{1, 2, 3, 0, 5, 6, 7, 4, 9, 10, 11, 8, 13, 14, 15, 12});

        Scanner scanner = new Scanner(System.in);
        ConsoleResultWriter consoleResultWriter = new ConsoleResultWriter(System.out, board);

        consoleResultWriter.writeBoard(board.getMatrix());

        while (!analyser.isComplete()) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("end")) {
                cancelGame(consoleResultWriter);
                break;
            }

            Direction direction = Direction.matchDirection(input);

            if (direction == null) {
                consoleResultWriter.write("Invalid direction!");
                continue;
            }

            board.move(direction);
            consoleResultWriter.writeBoard(board.getMatrix());
        }

        consoleResultWriter.write(analyser.isComplete() ? "Puzzle solved!" : "Try again later!");

        FileResultWriter resultWriter = new FileResultWriter(OUTPUT_FILE, board);

        resultWriter.writeAnalyser();

        consoleResultWriter.close();
        resultWriter.close();
    }

    private static void cancelGame(@NotNull final ConsoleResultWriter consoleResultWriter) {
        consoleResultWriter.newLine();
        consoleResultWriter.write("Game has been canceled!");
        consoleResultWriter.newLine();
    }

}

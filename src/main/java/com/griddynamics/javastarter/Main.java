package com.griddynamics.javastarter;

import com.griddynamics.javastarter.enums.Direction;
import com.griddynamics.javastarter.move.analyse.MoveAnalyser;
import com.griddynamics.javastarter.move.interfaces.Move;
import com.griddynamics.javastarter.result.ConsoleResultWriter;
import com.griddynamics.javastarter.result.FileResultWriter;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public final class Main {

    public static void main(@NotNull final String[] args) {

        MoveAnalyser analyser = new MoveAnalyser();
        Board board = new Board(new Random(), analyser);

        board.setMatrix(new int[]{1, 2, 3, 0, 5, 6, 7, 4, 9, 10, 11, 8, 13, 14, 15, 12});

        Scanner scanner = new Scanner(System.in);

        ConsoleResultWriter consoleResultWriter = new ConsoleResultWriter(System.out, board);

        consoleResultWriter.writeBoard(board.getMatrix());

        while (!board.getAnalyser().isComplete(board.getMatrix())) {

            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("end")) {
                consoleResultWriter.newLine();
                consoleResultWriter.write("Game has been canceled!");
                consoleResultWriter.newLine();
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

        consoleResultWriter.write(analyser.isComplete(board.getMatrix()) ? "Puzzle solved!" : "Try again later!");

        File file = new File("src/main/java/out.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

            FileResultWriter resultWriter = new FileResultWriter(writer, board);

            resultWriter.writeAnalyser();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

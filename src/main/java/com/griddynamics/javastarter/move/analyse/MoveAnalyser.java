package com.griddynamics.javastarter.move.analyse;

import com.griddynamics.javastarter.enums.Direction;
import com.griddynamics.javastarter.move.RandomTileMove;
import com.griddynamics.javastarter.move.TileMove;
import com.griddynamics.javastarter.move.interfaces.Move;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MoveAnalyser {

    private List<Move> moves = new ArrayList<>();

    public MoveAnalyser() {
    }

    /**
     * Checks if the move from specified index to specified direction is possible.
     *
     * @param matrix    Array of numbers representing a board
     * @param fromIndex Index of given array from which move is performed
     * @param direction Direction of the move
     * @return True if that move can be performed or false if not.
     */
    public boolean canMove(@NotNull final int[] matrix, final int fromIndex, @NotNull final Direction direction) {

        int indexMod = direction.getIndexMod();

        int length = matrix.length;
        int newIndex = fromIndex + indexMod;

        return newIndex < length && newIndex >= 0 && !pointsToEdge(fromIndex, direction);

    }

    /**
     * Creates and performs a new move.
     *
     * @param matrix         Array of numbers representing a board
     * @param emptyTileIndex Index of given array from which move is performed (actually index of tile that represents '0')
     * @param direction      Direction of the move
     * @return Array that represents a state after performing tile move
     */
    public int[] move(@NotNull final int[] matrix, final int emptyTileIndex, @NotNull final Direction direction) {

        TileMove tileMove = new TileMove(direction, emptyTileIndex, matrix);

        return move(tileMove);

    }

    /**
     * Performs given move and then save the state of this move
     *
     * @param move Move to perform
     * @return Array that represents a state after performing tile move
     */
    public int[] move(@NotNull final Move move) {

        move.process();
        moves.add(move);

        return move.getBoard();

    }

    /**
     * Performs move at random direction
     *
     * @param matrix         Array of numbers representing a board
     * @param emptyTileIndex Index of given array from which move is performed (actually index of tile that represents '0')
     * @param random         Random numbers generator
     * @return Array that represents a state after performing tile move
     */
    public int[] randomMove(@NotNull final int[] matrix, final int emptyTileIndex, @NotNull final Random random) {

        RandomTileMove randomTileMove = new RandomTileMove(matrix, emptyTileIndex, random);

        if (canMove(matrix, emptyTileIndex, randomTileMove.getDirection())) {
            return move(randomTileMove);
        }

        return matrix;

    }

    /**
     * Reverts last performed move
     *
     * @return Array that represents board after revert or null if none moves has been performed
     */
    @Nullable
    public int[] revertLastMove() {

        if (moves.size() > 0) {
            Move lastMove = moves.get(moves.size() - 1);
            lastMove.revert();
            moves.remove(lastMove);
            return lastMove.getBoard();
        }

        return null;
    }

    /**
     * Checks if the given board is solved
     *
     * @param matrix Representation of board
     * @return True if board is solved or false if not
     */
    public boolean isComplete(@NotNull final int[] matrix) {
        return Arrays.equals(matrix, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0});
    }

    @NotNull
    public List<Move> getMoves() {
        return new ArrayList<>(moves);
    }

    /**
     * Checks if the given tile is pointing to a board edge from given direction.
     *
     * @param tileIndex Index of tile
     * @param direction Direction for which state will be checked
     * @return True if direction is pointing to the edge or false if not
     */
    private boolean pointsToEdge(final int tileIndex, @NotNull final Direction direction) {

        if (direction == Direction.LEFT) {
            switch (tileIndex) {
                case 0:
                case 4:
                case 8:
                case 12:
                    return true;
                default:
                    return false;
            }
        } else if (direction == Direction.UP) {
            switch (tileIndex) {
                case 0:
                case 1:
                case 2:
                case 3:
                    return true;
                default:
                    return false;
            }
        } else if (direction == Direction.RIGHT) {
            switch (tileIndex) {
                case 3:
                case 7:
                case 11:
                case 15:
                    return true;
                default:
                    return false;
            }
        } else if (direction == Direction.DOWN) {
            switch (tileIndex) {
                case 12:
                case 13:
                case 14:
                case 15:
                    return true;
                default:
                    return false;
            }
        }
        return false;

    }
}

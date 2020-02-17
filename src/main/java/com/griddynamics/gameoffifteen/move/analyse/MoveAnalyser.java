package com.griddynamics.gameoffifteen.move.analyse;

import com.griddynamics.gameoffifteen.Board;
import com.griddynamics.gameoffifteen.enums.Direction;
import com.griddynamics.gameoffifteen.move.AbstractTileMove;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoveAnalyser {

    private List<AbstractTileMove> moves = new ArrayList<>();

    public MoveAnalyser() {
    }

    /**
     * Checks if the move from specified index to specified direction is possible.
     *
     * @param move Move to check
     * @return True if that move can be performed or false if not.
     */
    public boolean canMove(@NotNull final AbstractTileMove move) {
        final Direction direction = move.getDirection();
        int length = move.getBoard().length;
        int newIndex = move.getTo();

        return newIndex < length && newIndex >= 0 && !pointsToEdge(move.getFrom(), direction);
    }

    /**
     * Performs given move and then save the state of this move.
     *
     * @param move Move to perform
     * @return Array that represents a state after performing tile move
     * @deprecated Methods in analyser shouldn't change state of any move.
     */
    @Deprecated(forRemoval = true)
    public int[] move(@NotNull final AbstractTileMove move) {
        move.process();
        moves.add(move);

        return move.getBoard();
    }

    /**
     * Saves the move instruction and checks if the board is solved.
     *
     * @param move Performed move
     * @return true if board is solved or false if not, it depends of state of last performed move
     */
    public boolean analyse(@NotNull final AbstractTileMove move) {
        this.moves.add(move);
        return isComplete();
    }

    /**
     * Reverts last performed move.
     *
     * @return Array that represents board after revert or null if none moves has been performed
     * @deprecated Methods in analyser shouldn't change state of any move.
     */
    @Nullable
    @Deprecated(forRemoval = true)
    public int[] revertLastMove() {
        if (moves.size() > 0) {
            AbstractTileMove lastMove = moves.get(moves.size() - 1);
            lastMove.revert();
            moves.remove(lastMove);
            return lastMove.getBoard();
        }

        return null;
    }

    /**
     * Get last performed move.
     *
     * @return Last performed move or null if none has been performed
     */
    @Nullable
    public AbstractTileMove getLastMove() {
        if (moves.size() > 0) return moves.get(moves.size() - 1);
        return null;
    }

    /**
     * Checks if the last performed move board is solved.
     *
     * @return True if board is solved or false if not
     */
    public boolean isComplete() {
        AbstractTileMove lastMove = getLastMove();
        return lastMove != null && Arrays.equals(lastMove.getBoard(), Board.SOLVED_BOARD);
    }

    /**
     * Reason of why this method returns copy of the move register is to prevent any manual changes.
     * Change of state of any instance that is not a tail of the register will break integration of analyser.
     * (It won't show actual steps that have been made to the board)
     *
     * @return Copy of list of moves of this analyser
     */
    @NotNull
    public List<AbstractTileMove> getCopyOfMoves() {
        final ArrayList<AbstractTileMove> movesCopy = new ArrayList<>();

        this.moves.forEach(move -> movesCopy.add(move.clone()));

        return movesCopy;
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

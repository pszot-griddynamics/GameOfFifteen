package com.griddynamics.gameoffifteen.enums;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum Direction {
    UP(-4, "U"), RIGHT(1, "R"), DOWN(4, "D"), LEFT(-1, "L");

    private int indexMod;
    private String alias;

    Direction(final int indexMod, final String alias) {
        this.indexMod = indexMod;
        this.alias = alias;
    }

    public int getIndexMod() {
        return indexMod;
    }

    public String getAlias() {
        return alias;
    }

    /**
     * Match direction by it's name.
     *
     * @param string string representation of Direction
     * @return Direction based on parameterized string
     */
    @Nullable
    public static Direction matchDirection(@NotNull final String string) {
        switch (string.trim().toUpperCase()) {
            case "UP":
                return UP;
            case "RIGHT":
                return RIGHT;
            case "DOWN":
                return DOWN;
            case "LEFT":
                return LEFT;
            default:
                return matchByAlias(string);
        }
    }

    /**
     * Match direction by it's alias.
     *
     * @param alias alias of direction
     * @return Direction based on parameterized string
     */
    @Nullable
    public static Direction matchByAlias(@NotNull final String alias) {
        for (Direction direction : values()) {
            if (direction.getAlias().equalsIgnoreCase(alias)) return direction;
        }
        return null;
    }
}

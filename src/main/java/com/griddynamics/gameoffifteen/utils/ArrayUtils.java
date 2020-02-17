package com.griddynamics.gameoffifteen.utils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ArrayUtils {
    public static final String MATRIX_FORMAT =
            "[%2s %2s %2s %2s]\n"
                    + "[%2s %2s %2s %2s]\n"
                    + "[%2s %2s %2s %2s]\n"
                    + "[%2s %2s %2s %2s]";

    private ArrayUtils() {
    }

    /**
     * Converts give array of int to List of Integers.
     *
     * @param array give array to convert
     * @return List of Integers tha contains all elements of given array
     */
    @NotNull
    public static List<Integer> intArrayToList(@NotNull final int[] array) {
        List<Integer> list = new ArrayList<>();

        for (int anInt : array) {
            list.add(anInt);
        }

        return list;
    }

    /**
     * Format array of int as it's matrix representation.
     * example:
     * [ 0  1  2  3]
     * [ 3  5  6  7]
     * [ 8  9 10 11]
     * [12 13 14 15]
     *
     * @param array Array that'll be source of format arguments
     * @return Matrix created from parameterized array
     */
    public static String arrayToMatrixString(@NotNull final int[] array) {
        String[] formatArgs = Arrays.stream(array).mapToObj(Integer::toString).toArray(String[]::new);

        return String.format(MATRIX_FORMAT, formatArgs);
    }

}

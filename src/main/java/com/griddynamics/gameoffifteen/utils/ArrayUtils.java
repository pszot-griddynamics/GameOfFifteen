package com.griddynamics.gameoffifteen.utils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtils {

    /**
     * Converts give array of int to List of Integers
     *
     * @param array give array to convert
     * @return List of Integers tha contains all elements of given array
     */
    @NotNull
    public static List<Integer> intArrayToList(@NotNull int[] array) {
        List<Integer> list = new ArrayList<>();

        for (int anInt : array) {
            list.add(anInt);
        }

        return list;
    }

}

package com.griddynamics.javastarter.move.interfaces;

import com.griddynamics.javastarter.enums.Direction;

public interface Move extends Instruction {
    Direction getDirection();
    int[] getBoard();
    int getFrom();
    int getTo();
}

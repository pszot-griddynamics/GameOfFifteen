package com.griddynamics.gameoffifteen.move.interfaces;

import com.griddynamics.gameoffifteen.enums.Direction;

public interface Move extends Instruction {
    Direction getDirection();
    int[] getBoard();
    int getFrom();
    int getTo();
}

package com.griddynamics.gameoffifteen.move.interfaces;

import com.griddynamics.gameoffifteen.enums.Direction;

public interface Move extends Instruction, Cloneable {

    Direction getDirection();

    int[] getBoard();

    int getFrom();

    int getTo();

}

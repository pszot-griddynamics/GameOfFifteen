package com.griddynamics.gameoffifteen.move.interfaces;

public interface Instruction {
    /**
     * Process instruction logic.
     */
    void process();

    /**
     * Revert any change made by this instruction according to {@code process()} method.
     */
    void revert();
}

package by.epam.lab.beans;

import static by.epam.lab.utils.Constants.*;

public class StrongTrial extends Trial {

    public StrongTrial(String name, int mark1, int mark2) {
        super(name, mark1, mark2);
    }

    public StrongTrial(Trial trial) {
        super(trial);
    }

    @Override
    public Trial getClone() {
        return new StrongTrial(this);
    }

    @Override
    public boolean isPassed() {
        return getMark1() / TWO_FOR_DELETE + getMark2() >= CONSTANTS_FOR_TRIAL;
    }
}

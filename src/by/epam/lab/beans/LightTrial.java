package by.epam.lab.beans;

import static by.epam.lab.utils.Constants.*;

public class LightTrial extends Trial {

    public LightTrial(String name, int mark1, int mark2) {
        super(name, mark1, mark2);
    }

    @Override
    public boolean isPassed() {
        return getMark1() >= CONSTANTS_FOR_LIGHT_TRIAL && getMark2() >= CONSTANTS_FOR_LIGHT_TRIAL;
    }

    @Override
    public Trial getClone() {
        return super.getClone();
    }

    @Override
    public void clearMarks() {
        super.clearMarks();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

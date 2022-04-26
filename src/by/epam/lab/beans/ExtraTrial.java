package by.epam.lab.beans;

import static by.epam.lab.utils.Constants.*;

public class ExtraTrial extends Trial {
    private int mark3;
    public static final int CONSTANTS_FOR_EXTRA_TRIAL = 37;

    public ExtraTrial(String name, int mark1, int mark2, int mark3) {
        super(name, mark1, mark2);
        this.mark3 = mark3;
    }

    public int getMark3() {
        return mark3;
    }

    public void setMark3(int mark3) {
        this.mark3 = mark3;
    }

    @Override
    public boolean isPassed() {
        return super.isPassed() && mark3 >= CONSTANTS_FOR_EXTRA_TRIAL;
    }

    @Override
    public Trial getClone() {
        return new ExtraTrial(getName(), getMark1(), getMark2(), getMark3());
    }

    @Override
    public void clearMarks() {
        super.clearMarks();
        mark3 = ZERO;
    }

    @Override
    public String toString() {
        return super.toString() + DELIMITER + mark3;
    }
}

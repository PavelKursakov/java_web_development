package by.epam.lab.beans;

import static by.epam.lab.utils.Constants.*;

public class Trial {
    private String name;
    private int mark1;
    private int mark2;
    public static final int CONSTANTS_FOR_TRIAL = 125;

    public Trial() {
    }

    public Trial(Trial trial) {
        this(trial.getName(), trial.getMark1(), trial.getMark2());
    }

    public Trial(String name, int mark1, int mark2) {
        this.name = name;
        this.mark1 = mark1;
        this.mark2 = mark2;
    }

    public Trial(String[] elements) {
        this(elements[0], Integer.parseInt(elements[1]), Integer.parseInt(elements[2]));
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMark1() {
        return mark1;
    }

    public void setMark1(int mark1) {
        this.mark1 = mark1;
    }

    public int getMark2() {
        return mark2;
    }

    public void setMark2(int mark2) {
        this.mark2 = mark2;
    }

    public boolean isPassed() {
        return mark1 + mark2 >= CONSTANTS_FOR_TRIAL;
    }

    public Trial getClone() {
        return new Trial(this);
    }

    public void clearMarks() {
        mark1 = ZERO;
        mark2 = ZERO;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + DELIMITER + name + DELIMITER + mark1 + DELIMITER + mark2;
    }
}

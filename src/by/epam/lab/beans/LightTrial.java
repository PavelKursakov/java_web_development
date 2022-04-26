package by.epam.lab.beans;

public class LightTrial extends Trial {
    private static final int CONSTANTS_FOR_LIGHT_TRIAL = 49;

    public LightTrial(String name, int mark1, int mark2) {
        super(name, mark1, mark2);
    }

    public LightTrial(Trial trial) {
        super(trial);
    }

    @Override
    public boolean isPassed() {
        return getMark1() >= CONSTANTS_FOR_LIGHT_TRIAL && getMark2() >= CONSTANTS_FOR_LIGHT_TRIAL;
    }

    @Override
    public Trial getClone() {
        return new LightTrial(this);
    }
}

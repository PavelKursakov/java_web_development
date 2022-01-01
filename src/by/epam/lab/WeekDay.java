package by.epam.lab;

import java.util.Locale;

public enum WeekDay {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY;

    @Override
    public String toString() {
        return name().toLowerCase(Locale.ROOT);
    }
}

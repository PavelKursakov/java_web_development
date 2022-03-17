package by.epam.lab.enums;

import static by.epam.lab.utils.Constants.*;

public enum MarkType {
    INT_MARK {
        @Override
        public String getStringMark(int i) {
            return String.format(INT_MARK_FORMAT, i / TEN_FOR_INT_MAR);
        }
    },
    HALF_MARK {
        @Override
        public String getStringMark(int i) {
            return i % TEN_FOR_INT_MAR == FIVE_FOR_EQUALS ? String.format(DOUBLE_MARK_FORMAT,
                    i / TEN_FOR_INT_MAR, i % TEN_FOR_INT_MAR) :
                    String.format(INT_MARK_FORMAT, i / TEN_FOR_INT_MAR);
        }
    },
    DOUBLE_MARK {
        @Override
        public String getStringMark(int i) {
            return String.format(DOUBLE_MARK_FORMAT, i / TEN_FOR_INT_MAR, i % TEN_FOR_INT_MAR);
        }
    };

    public abstract String getStringMark(int i);
}

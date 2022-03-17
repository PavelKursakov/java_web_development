package by.epam.lab.enums;
import static by.epam.lab.utils.Constants.*;

public enum MarkType {
    INT_MARK{
        @Override
        public String getStringMark(int i) {
            return String.format(INT_MARK_FORMAT, i / TEN_FOR_INT_MAR);
        }
    },
    HALF_MARK{
        @Override
        public String getStringMark(int i) {
            String[] str = Integer.toString(i).split(REGEX_FOR_SPLIT);
            if (str[str.length -1].equals(FIVE_FOR_EQUALS)) {
                return String.format(DOUBLE_MARK_FORMAT, i / TEN_FOR_INT_MAR, i % TEN_FOR_INT_MAR);
            } else {
                return String.format(INT_MARK_FORMAT, i / TEN_FOR_INT_MAR);
            }
        }
    },
    DOUBLE_MARK{
        @Override
        public String getStringMark(int i) {
            return String.format(DOUBLE_MARK_FORMAT, i / TEN_FOR_INT_MAR, i % TEN_FOR_INT_MAR);
        }
    };
    public abstract String getStringMark(int i);
}

package by.epam.lub.beans;

import static by.epam.lub.constants.Constants.*;

public class LenNum {
    private final int len;
    private int num;

    public LenNum(int len) {
        this.len = len;
        num = 1;
    }

    public int getLen() {
        return len;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void incNum() {
        num++;
    }

    @Override
    public String toString() {
        return len + DELIMITER + num;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final LenNum other = (LenNum) obj;
        if (len != other.len)
            return false;
        other.incNum();
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 17;
        int result = 1;
        result = PRIME * result + len;
        return result;
    }
}

package by.epam.lab.beans;

import by.epam.lab.constants.Constants;

public class LenNum {
    private int len;
    private int num;

    public LenNum() {
    }

    public LenNum(int len, int num) {
        this.len = len;
        this.num = num;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return len + Constants.DELIMITER + num;
    }
}

package by.epam.lub.beans;

public class LenNum implements Comparable<LenNum> {
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
        return len + ";" + num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LenNum lenNum = (LenNum) o;
        return len == lenNum.len;
    }

    @Override
    public int compareTo(LenNum lenNum) {
        return len - lenNum.len;
    }
}

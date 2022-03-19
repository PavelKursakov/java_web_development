package by.epam.lab.beans;

import java.io.Closeable;

public interface ResultDao extends Closeable {
    Result nextResult();
    boolean hasResult();
}

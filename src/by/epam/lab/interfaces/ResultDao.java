package by.epam.lab.interfaces;

import by.epam.lab.beans.Result;

import java.io.Closeable;

public interface ResultDao extends Closeable {
    Result nextResult();

    boolean hasResult();
}

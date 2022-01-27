package by.epam.lab.exceptions;

import by.epam.lab.Constants;

public class CsvLineException extends Exception {
    private final String csvName;
    private final String wrongLine;
    private final String wrongElement;

    public CsvLineException() {
        this(null, null, null);
    }

    public CsvLineException(String csvName, String wrongLine) {
        this.csvName = csvName;
        this.wrongLine = wrongLine;
        this.wrongElement = Constants.EMPTY_STRING;
    }

    public CsvLineException(String csvName, String wrongLine, String wrongElement) {
        this.csvName = csvName;
        this.wrongLine = wrongLine;
        this.wrongElement = wrongElement;
    }

    public String getCsvName() {
        return csvName;
    }

    public String getWrongLine() {
        return wrongLine;
    }

    public String getWrongElement() {
        return wrongElement;
    }

    @Override
    public String toString() {
        return csvName + Constants.SPACE + wrongLine + Constants.SPACE + wrongElement;
    }
}

package by.epam.lab.exceptions;

import java.io.FileNotFoundException;

public class SourceException extends FileNotFoundException {
    public SourceException (String message){
        super(message);
    }
}

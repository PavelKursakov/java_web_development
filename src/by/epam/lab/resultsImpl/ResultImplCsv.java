package by.epam.lab.resultsImpl;

import by.epam.lab.beans.Result;
import by.epam.lab.interfaces.ResultDao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static by.epam.lab.utils.Constants.*;

public class ResultImplCsv implements ResultDao {
    private Scanner sc;

    public ResultImplCsv(String csvName) {
        try {
            sc = new Scanner(new FileReader(csvName));
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }

    @Override
    public Result nextResult() {
        return new Result(sc.next().split(DELIMITER));
    }

    @Override
    public boolean hasResult() {
        return sc.hasNextLine();
    }

    public void close() {
        sc.close();
    }
}

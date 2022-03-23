package by.epam.lab.resultsImpl;

import by.epam.lab.beans.Result;
import by.epam.lab.exceptions.SourceException;
import by.epam.lab.factorys.ResultFactory;
import by.epam.lab.interfaces.ResultDao;
import by.epam.lab.utils.Constants;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static by.epam.lab.utils.Constants.*;

public class ResultImplCsv implements ResultDao {
    private Scanner sc;
    private ResultFactory factory;

    public ResultImplCsv(String name, ResultFactory factory) throws SourceException {
        try {
            sc = new Scanner(new FileReader(SOURCE_DIR + name + FILE_EXT));
            this.factory = factory;
        } catch (FileNotFoundException e) {
            throw new SourceException(e.getMessage());
        }
    }

    @Override
    public boolean hasResult() {
        return sc.hasNext();
    }

    @Override
    public Result nextResult() {
        String[] lineRes = sc.nextLine().split(DELIMITER);
        return factory.getResultFromFactory(
                lineRes[LOGIN_ID_ELEMENT],
                lineRes[TEST_ID_ELEMENT],
                lineRes[DATE_ID_ELEMENT],
                lineRes[MARK_ID_ELEMENT]);
    }

    @Override
    public void close() {
        sc.close();
    }

}

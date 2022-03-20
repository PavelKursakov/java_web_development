package by.epam.lab.resultsImpl;

import by.epam.lab.beans.Result;
import by.epam.lab.beans.ResultHandler;
import by.epam.lab.interfaces.ResultDao;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ResultImplXml implements ResultDao {
    private List<Result> resultList;
    private int index = 0;

    public ResultImplXml(String resultsName) {
        try {
            ResultHandler handler = new ResultHandler();
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
            try {
                reader.parse(resultsName);
                resultList = handler.getResults();
            } catch (FileNotFoundException e) {
                resultList = new LinkedList<>();
                System.err.println(e);
            }

        } catch (IOException | SAXException e) {
            System.err.println(e);
        }
    }

    @Override
    public Result nextResult() {
        Result result = resultList.get(index);
        if (hasResult()) {
            index++;
        }
        return result;
    }

    @Override
    public boolean hasResult() {
        return index + 1 < resultList.size() && resultList.get(index + 1) != null;
    }

    public void close() {

    }
}

package by.epam.lab.beans;

import by.epam.lab.factorys.ResultFactory;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.LinkedList;

import static by.epam.lab.utils.Constants.*;

public class ResultHandler extends DefaultHandler {
    private enum ResultEnum {
        RESULTS, STUDENT, LOGIN, TESTS, TEST;
    }

    private LinkedList<Result> results = new LinkedList<>();
    private ResultEnum currentEnum;
    private String login;
    private ResultFactory resultFactory;

    public LinkedList<Result> getResults() {
        return results;
    }

    public ResultHandler(ResultFactory resultFactory) {
        this.resultFactory = resultFactory;
    }

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) {
        currentEnum = ResultEnum.valueOf(localName.toUpperCase());
        if (currentEnum == ResultEnum.TEST) {
            Result result = resultFactory.getResultFromFactory(
                    login,
                    attributes.getValue(TEST_ID_FOR_RESULT_HANDLER),
                    attributes.getValue(DATE_ID_FOR_RESULT_HANDLER),
                    attributes.getValue(MARK_ID_FOR_RESULT_HANDLER));
            results.add(result);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String str = new String(ch, start, length).trim();
        if (currentEnum == ResultEnum.LOGIN && !str.isEmpty()) {
            login = str;
        }
    }
}

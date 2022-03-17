package by.epam.lab.beans;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
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

    public LinkedList<Result> getResults() {
        return results;
    }

    public ResultEnum getCurrentEnum() {
        return currentEnum;
    }

    public String getLogin() {
        return login;
    }

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        currentEnum = ResultEnum.valueOf(localName.toUpperCase());
        if (currentEnum == ResultEnum.TEST) {
            results.add(new Result(login, attributes.getValue(TEST_ID),
                    attributes.getValue(DATE_ID), attributes.getValue(MARK_ID)));
        }
    }

    public void characters(char[] ch, int start, int length) {
        String str = new String(ch, start, length).trim();
        if (currentEnum == ResultEnum.LOGIN && !str.isEmpty()) {
            login = str;
        }
    }
}

package by.epam.lab.beans;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import static by.epam.lab.utils.Constants.*;

public class ResultHandler extends DefaultHandler {
    private enum ResultEnum {
        RESULTS, STUDENT, LOGIN, TESTS, TEST;
    }

    private List<Result> results = new ArrayList<>();
    private ResultEnum currentEnum;
    private String login;

    public List<Result> getResults() {
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
                    Date.valueOf(attributes.getValue(DATE_ID)),
                    (int) (Double.parseDouble(attributes.getValue(MARK_ID)) * TEN_FOR_INT_MAR)));
        }
    }

    public void characters(char[] ch, int start, int length) {
        String str = new String(ch, start, length).trim();
        if (currentEnum == ResultEnum.LOGIN && !str.isEmpty()) {
            login = str;
        }
    }
}

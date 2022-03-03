import by.epam.lab.beans.Result;
import by.epam.lab.beans.ResultHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import static by.epam.lab.utils.Constants.*;

import java.io.IOException;


public class Runner {
    public static void main(String[] args) {
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            ResultHandler handler = new ResultHandler();
            reader.setContentHandler(handler);
            reader.parse(RESULTS_NAME);
            for (Result result : handler.getResults()) {
                System.out.println(result);
            }
        } catch (SAXException | IOException e) {
            System.out.println(e);
        }
    }
}

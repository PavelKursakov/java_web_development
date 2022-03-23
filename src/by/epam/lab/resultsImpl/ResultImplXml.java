package by.epam.lab.resultsImpl;

import by.epam.lab.beans.Result;
import by.epam.lab.beans.ResultHandler;
import by.epam.lab.exceptions.ParseRuntimeException;
import by.epam.lab.exceptions.SourceException;
import by.epam.lab.factorys.ResultFactory;
import by.epam.lab.interfaces.ResultDao;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Iterator;
import static by.epam.lab.utils.Constants.*;

public class ResultImplXml implements ResultDao {
    private Iterator<Result> resultIterator;

    public ResultImplXml(String fileNameXml, ResultFactory resultFactory) throws SourceException {
        try {
            String fileNameXmlFull = SOURCE_DIR + fileNameXml + XML;
            XMLReader reader = XMLReaderFactory.createXMLReader();
            ResultHandler handler = new ResultHandler(resultFactory);
            reader.setContentHandler(handler);
            reader.parse(fileNameXmlFull);
            resultIterator = handler.getResults().iterator();
        } catch (IOException e) {
            throw new SourceException(e.getMessage());
        } catch (SAXException e) {
            throw new ParseRuntimeException(WRONG_DATA_IN_XML_FILE);
        }
    }

    @Override
    public Result nextResult() {
        return resultIterator.next();
    }

    @Override
    public boolean hasResult() {
        return resultIterator.hasNext();
    }

    @Override
    public void close() {
        resultIterator = null;
    }
}

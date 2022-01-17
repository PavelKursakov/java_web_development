import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class TestRunner {
    private final static String FILE_IS_NOT_FOUND = "file is not found";
    private final static String REGEX_FOR_INDEX_LINE = "(index)[1-9]\\d*\\s*";
    private final static String REGEX_FOR_INDEX_VALUE = "\\s*[1-9]\\d*\\s*";
    private final static String REGEX_FOR_INDEX_WORD = "(index).*";
    private final static String INDEX = "index";
    private final static String VALUE = "value";

    private static int getResult(String fileName, StringBuilder sumStr)
            throws FileNotFoundException {
        ResourceBundle rb = ResourceBundle.getBundle(fileName);
        Enumeration<String> keys = rb.getKeys();
        String key;
        int errorLines = 0;
        double sum = 0.0;
        while (keys.hasMoreElements()) {
            key = keys.nextElement();
            if (key.matches(REGEX_FOR_INDEX_WORD)) {
                try {
                    String keyValue = rb.getString(key);
                    if (key.matches(REGEX_FOR_INDEX_LINE) &&
                            keyValue.matches(REGEX_FOR_INDEX_VALUE)) {
                        String i = key.substring(INDEX.length());
                        sum += Double.parseDouble(rb.getString(VALUE + i + keyValue));
                    } else {
                        errorLines++;
                    }

                } catch (MissingResourceException | NumberFormatException e) {
                    errorLines++;
                }
            }
        }
        sumStr.append(sum);
        return errorLines;
    }

    @Test
    public void testMainScenarioFileIn1 () throws FileNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();
        final String fileName = "in1";
        int expected = 3;
        final String expectedSumStr = "8.24";
        Assert.assertEquals(expected,getResult(fileName,stringBuilder));
        Assert.assertEquals(expectedSumStr,stringBuilder.toString());
    }

    @Test
    public void testMainScenarioFileIn2 () throws FileNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();
        final String fileName = "in2";
        int expected = 9;
        final String expectedSumStr = "30.242";
        Assert.assertEquals(expected,getResult(fileName,stringBuilder));
        Assert.assertEquals(expectedSumStr,stringBuilder.toString());
    }

    @Test(expected = MissingResourceException.class)
    public void testFileIsNotFound() throws FileNotFoundException{
        final String fileName = "in10";
        getResult(fileName, new StringBuilder());
    }
}

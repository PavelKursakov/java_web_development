import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRunner {
    private static Result getResult(String fileName) throws FileNotFoundException {
        final String REGEX_FOR_INDEX_VALUE = "[1-9]\\d*";
        final int TAIL_INDEX = 1;
        final String VALUE = "value";
        final String REGEX_FOR_INDEX_WORD = "index(.*)";
        int errorLines = 0;
        ResourceBundle rb = ResourceBundle.getBundle(fileName);
        Enumeration<String> keys = rb.getKeys();
        String key;
        double sum = 0.0;
        while (keys.hasMoreElements()) {
            key = keys.nextElement();
            Pattern pattern = Pattern.compile(REGEX_FOR_INDEX_WORD);
            Matcher matcherKey = pattern.matcher(key);
            if (matcherKey.matches()) {
                String j = rb.getString(key).trim();
                String i = matcherKey.group(TAIL_INDEX);
                Pattern patternIJ = Pattern.compile(REGEX_FOR_INDEX_VALUE);
                Matcher matcherI = patternIJ.matcher(i);
                Matcher matcherJ = patternIJ.matcher(j);
                if (matcherI.matches() && matcherJ.matches()) {
                    String valueIJ = VALUE + i + j;
                    try {
                        sum += Double.parseDouble(rb.getString(valueIJ));
                    } catch (MissingResourceException | NumberFormatException e) {
                        errorLines++;
                    }
                } else {
                    errorLines++;
                }
            }
        }
        return new Result(sum, errorLines);
    }

    static class Result {
        private double sum;
        private int errorLines;

        public Result(double sum, int errorLines) {
            this.sum = sum;
            this.errorLines = errorLines;
        }

        public Result() {
        }

        public double getSum() {
            return sum;
        }

        public int getErrorLines() {
            return errorLines;
        }

    }

    @Test
    public void testMainScenarioFileIn1() throws FileNotFoundException {
        final String fileName = "in1";
        Result result = getResult(fileName);
        int expected = 3;
        final double expectedSum = 8.24;
        Assert.assertEquals(expected, result.getErrorLines());
        Assert.assertEquals(expectedSum, result.getSum(), 0);
    }

    @Test
    public void testMainScenarioFileIn2() throws FileNotFoundException {
        final String fileName = "in2";
        Result result = getResult(fileName);
        int expected = 9;
        final double expectedSum = 30.242;
        Assert.assertEquals(expected, result.getErrorLines());
        Assert.assertEquals(expectedSum, result.getSum(), 0);
    }

    @Test(expected = MissingResourceException.class)
    public void testFileIsNotFound1() throws FileNotFoundException {
        final String fileName = "in10";
        getResult(fileName);
    }

    @Test(expected = MissingResourceException.class)
    public void testFileIsNotFound2() throws FileNotFoundException {
        final String fileName = "in0";
        getResult(fileName);
    }
}

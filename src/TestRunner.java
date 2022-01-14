import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class TestRunner {
    private static final String RESULT_HEAD = "result(";
    private static final String RESULT_TAIL = ") = ";
    private static final String BEFORE_SIGN = " ";
    private static final String AFTER_SIGN = " ";
    private static final String PLUS = BEFORE_SIGN + "+" + AFTER_SIGN;
    private static final String MINUS = BEFORE_SIGN + "-" + AFTER_SIGN;

    private static int getResult(String csvName, StringBuilder result) throws FileNotFoundException {
        int errorLines = 0;
        double sum = 0.0;
        final String SEMICOLON = ";";

        try (Scanner scanner = new Scanner(new FileReader(csvName))) {
            scanner.useLocale(Locale.ENGLISH);
            while (scanner.hasNextLine()) {
                try {
                    String currentLine = scanner.nextLine();
                    String[] elements = currentLine.split(SEMICOLON);
                    int index = Integer.parseInt(elements[0]);
                    double element = Double.parseDouble(elements[index]);
                    sum += element;
                    result.append(element >= 0 ? PLUS : MINUS).append(Math.abs(element));

                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    errorLines++;
                }
            }
            if (result.length() > 0) {
                final int SIGN_LENGTH = MINUS.length();
                final char CHAR_MINUS = '-';
                String symbol = result.substring(0, SIGN_LENGTH);
                result.delete(0, SIGN_LENGTH);
                if (symbol.equals(MINUS)) {
                    result.insert(0, CHAR_MINUS);
                }
            }
            result.insert(0, RESULT_HEAD).append(RESULT_TAIL).append(sum);
        }
        return errorLines;
    }

    @Test
    public void testMainScenarioIn1() throws FileNotFoundException {
        String csvName1 = "src/in1.csv";
        String result = String.format("5.2%s3.14%s0.0",MINUS,PLUS);
        String sum = "2.06";
        StringBuilder result1 = new StringBuilder();
        int errorLines1 = getResult(csvName1, result1);
        Assert.assertEquals(3, errorLines1);
        String expectedIn1 = String.format("%s%s%s%s", RESULT_HEAD, result, RESULT_TAIL, sum);
        Assert.assertEquals(expectedIn1, result1.toString());
    }

    @Test
    public void testMainScenarioIn2() throws FileNotFoundException {
        String csvName2 = "src/in2.csv";
        String result = String.format("-3.1%s1.0",MINUS);
        String sum = "-4.1";
        StringBuilder result2 = new StringBuilder();
        int errorLines2 = getResult(csvName2, result2);
        Assert.assertEquals(0, errorLines2);
        String expectedIn2 = String.format("%s%s%s%s", RESULT_HEAD, result, RESULT_TAIL, sum);
        Assert.assertEquals(expectedIn2, result2.toString());
    }

    @Test
    public void testMainScenarioIn3() throws FileNotFoundException {
        String csvName3 = "src/in3.csv";
        String result = "0.75";
        String sum = "0.75";
        StringBuilder result3 = new StringBuilder();
        int errorLines3 = getResult(csvName3, result3);
        Assert.assertEquals(0, errorLines3);
        String expectedIn3 = String.format("%s%s%s%s", RESULT_HEAD, result, RESULT_TAIL, sum);
        Assert.assertEquals(expectedIn3, result3.toString());
    }

    @Test
    public void testMainScenarioIn4() throws FileNotFoundException {
        String csvName4 = "src/in4.csv";
        String result = "0.0";
        String sum = "0.0";
        StringBuilder result4 = new StringBuilder();
        int errorLines4 = getResult(csvName4, result4);
        Assert.assertEquals(0, errorLines4);
        String expectedIn4 = String.format("%s%s%s%s", RESULT_HEAD, result, RESULT_TAIL, sum);
        Assert.assertEquals(expectedIn4, result4.toString());
    }

    @Test
    public void testMainScenarioIn5() throws FileNotFoundException {
        String csvName5 = "src/in5.csv";
        String result = "";
        String sum = "0.0";
        StringBuilder result5 = new StringBuilder();
        int errorLines5 = getResult(csvName5, result5);
        Assert.assertEquals(1, errorLines5);
        String expectedIn5 = String.format("%s%s%s%s", RESULT_HEAD, result, RESULT_TAIL, sum);
        Assert.assertEquals(expectedIn5, result5.toString());
    }


    @Test(expected = FileNotFoundException.class)
    public void testWrongCsvName() throws FileNotFoundException {
        final String csvName = "src/in10.csv";
        getResult(csvName, new StringBuilder());
    }
}

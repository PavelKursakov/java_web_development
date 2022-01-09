import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class TestRunner {
    private static int getResult(String csvName, StringBuilder result) throws FileNotFoundException {
        int errorLines = 0;
        double sum = 0.0;
        final String RESULT_HEAD = "result(";
        final String RESULT_TAIL = ") = ";
        final String SEMICOLON = ";";
        final String PLUS = " + ";
        final String MINUS = " - ";
        final String ERROR_LINES = "error lines = ";
        final String TABULATION = "\n";

        try (Scanner scanner = new Scanner(new FileReader(csvName))) {
            scanner.useLocale(Locale.ENGLISH);
            while (scanner.hasNextLine()) {
                try {
                    String currentLine = scanner.nextLine();
                    String[] elements = currentLine.split(SEMICOLON);
                    int index = Integer.parseInt(elements[0]);
                    double element = Double.parseDouble(elements[index]);
                    sum += element;
                    if (result.toString().isEmpty()) {
                        result.append(element);
                        continue;
                    }

                    if (element >= 0) {
                        result.append(PLUS).append(element);
                    } else {
                        element = element * -1;
                        result.append(MINUS).append(element);
                    }

                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    errorLines++;
                }
            }
            result.insert(0, RESULT_HEAD).append(RESULT_TAIL).append(sum).
                    append(TABULATION).append(ERROR_LINES).append(errorLines);
            System.out.println(result);
        }
        return errorLines;
    }

    @Test
    public void testMainScenario() throws FileNotFoundException {
        final String csvName = "src/in1.csv";
        StringBuilder result = new StringBuilder();
        int errorLines = getResult(csvName, result);
        Assert.assertEquals(3, errorLines);
        String expectedIn1 = "result(5.2 - 3.14 + 0.0) = 2.06\n" +
                "error lines = 3";
        Assert.assertEquals(expectedIn1, result.toString());
    }

    @Test(expected = FileNotFoundException.class)
    public void testWrongCsvName() throws FileNotFoundException {
        final String csvName = "src/in10.csv";
        getResult(csvName, new StringBuilder());
    }

}

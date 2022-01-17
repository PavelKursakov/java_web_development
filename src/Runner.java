import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Runner {
    private final static String NAME_IN = "in";
    private final static String FILE_IS_NOT_FOUND = "file is not found";
    private final static String REGEX_FOR_INDEX_LINE = "(index)[1-9]\\d*\\s*";
    private final static String REGEX_FOR_INDEX_VALUE = "\\s*[1-9]\\d*\\s*";
    private final static String REGEX_FOR_INDEX_WORD = "(index).*";
    private final static String INDEX = "index";
    private final static String VALUE = "value";

    public static void main(String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle(NAME_IN);
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
        System.out.println(sum);
        System.out.println(errorLines);
    }
}

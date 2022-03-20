import by.epam.lab.factorys.DecimalResultFactory;

import static by.epam.lab.utils.Constants.*;

public class RunnerXml {
    public static void main(String[] args) {
        DecimalResultFactory decimalResultFactory = new DecimalResultFactory();
        RunnerLogic.logicMethod(RESULTS_NAME, decimalResultFactory);
    }
}

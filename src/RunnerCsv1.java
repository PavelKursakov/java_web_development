import by.epam.lab.factorys.ResultFactory;

import static by.epam.lab.utils.Constants.*;

public class RunnerCsv1 {
    public static void main(String[] args) {
        ResultFactory resultFactory = new ResultFactory();
        RunnerLogic.logicMethod(CSV_NAME, resultFactory);
    }
}

import by.epam.lab.factorys.HalfResultFactory;

import static by.epam.lab.utils.Constants.*;

public class RunnerCsv2 {
    public static void main(String[] args) {
        HalfResultFactory halfResultFactory = new HalfResultFactory();
        RunnerLogic.logicMethod(CSV_NAME2, halfResultFactory);
    }
}

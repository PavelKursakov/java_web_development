import by.epam.lab.beans.ExtraTrial;
import by.epam.lab.beans.LightTrial;
import by.epam.lab.beans.StrongTrial;
import by.epam.lab.beans.Trial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import static by.epam.lab.utils.Constants.*;

public class Runner {
    public static void main(String[] args) {
        List<Trial> trials = Arrays.asList(
                (new Trial("Pavel", 95, 75)),
                (new Trial("Denis", 87, 91)),
                (new Trial("Vladislav", 41, 54)),
                (new LightTrial("Oleg", 59, 72)),
                (new LightTrial("Evgeniy", 0, 0)),
                (new StrongTrial("Alexander", 3, 9)),
                (new StrongTrial("Mariya", 94, 87)),
                (new ExtraTrial("Roman", 57, 91, 19)),
                (new ExtraTrial("Olga", 74, 55, 91)));
        ToIntFunction<Trial> getResultOp =
                trial -> trial.getMark1() + trial.getMark2();
        trials.
                forEach(System.out::println);
        System.out.println(EMPTY_STRING);
        System.out.println(PASSED_TESTS_COUNT);
        System.out.println(trials.
                stream().
                filter(Trial::isPassed).
                count());
        System.out.println(EMPTY_STRING);
        System.out.println(SUM_OF_MARKS_AFTER_SORTING);
        trials.
                sort(Comparator.comparingInt(getResultOp));
        trials.
                stream().
                mapToInt(getResultOp).
                forEach(System.out::println);
        System.out.println(EMPTY_STRING);
        System.out.println(FAILED_TESTS);
        List<Trial> failedTrials =
                trials.
                        stream().
                        filter(trial -> !trial.isPassed()).
                        map(Trial::getClone).
                        peek(Trial::clearMarks).
                        peek(System.out::println).
                        collect(Collectors.toList());
        boolean allTrialsAreFailed = failedTrials.
                stream().
                noneMatch(Trial::isPassed);
        System.out.println(EMPTY_STRING);
        System.out.println(ALL_TRIALS_ARE_FAILED + allTrialsAreFailed);
        System.out.println(EMPTY_STRING);
        int[] sum = trials.
                stream().
                mapToInt(getResultOp).
                toArray();
        String results = Arrays.
                stream(sum).
                mapToObj(String::valueOf).
                collect(Collectors.joining(DELIMITER));
        System.out.println(ARRAY_FROM_SUMS);
        System.out.println(results);
    }
}

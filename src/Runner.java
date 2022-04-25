import by.epam.lab.beans.ExtraTrial;
import by.epam.lab.beans.LightTrial;
import by.epam.lab.beans.StrongTrial;
import by.epam.lab.beans.Trial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static by.epam.lab.utils.Constants.*;

public class Runner {
    public static void main(String[] args) {
        List<Trial> trials = new ArrayList<>();
        trials.add(new Trial("Pavel", 95, 75));
        trials.add(new Trial("Denis", 87, 91));
        trials.add(new Trial("Vladislav", 41, 54));
        trials.add(new LightTrial("Oleg", 59, 72));
        trials.add(new LightTrial("Evgeniy", 0, 0));
        trials.add(new StrongTrial("Alexander", 3, 9));
        trials.add(new StrongTrial("Mariya", 94, 87));
        trials.add(new ExtraTrial("Roman", 57, 91, 19));
        trials.add(new ExtraTrial("Olga", 74, 55, 91));
        trials.forEach(System.out::println);
        System.out.println();
        System.out.println(trials.stream().
                filter(Trial::isPassed).
                count());
        System.out.println();
        trials.sort(Comparator.comparingInt(Trial::getSum));
        trials.forEach(trial -> System.out.println(trial.getSum()));
        System.out.println();
        List<Trial> failedTrials = new ArrayList<>();
        trials.stream().
                filter(trial -> !trial.isPassed()).
                forEach(trial -> failedTrials.add(trial.getClone()));
        failedTrials.forEach(Trial::clearMarks);
        System.out.println(failedTrials);
        boolean allTrialsAreFailed = failedTrials.stream().
                noneMatch(Trial::isPassed);
        System.out.println();
        System.out.println(ALL_TRIALS_ARE_FAILED + allTrialsAreFailed);
        int[] sum = trials.stream().
                mapToInt(Trial::getSum).
                toArray();
        System.out.println(Arrays.toString(sum));
    }
}

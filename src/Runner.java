import by.epam.lab.threads.TrialConsumer;
import by.epam.lab.services.TrialBuffer;
import by.epam.lab.threads.TrialProducer;

import java.io.FileNotFoundException;

import static by.epam.lab.utils.Constants.*;

public class Runner {
    public static void main(String[] args) {
        try {
            TrialBuffer trialBuffer = new TrialBuffer();
            (new Thread(new TrialProducer(trialBuffer, CSV_NAME))).start();
            (new Thread(new TrialConsumer(trialBuffer))).start();
        } catch (FileNotFoundException e) {
            System.out.println(FILE_IS_NOT_FOUND);
        }
    }
}

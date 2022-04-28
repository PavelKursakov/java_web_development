import by.epam.lab.threads.TrialConsumer;
import by.epam.lab.services.TrialBuffer;
import by.epam.lab.threads.TrialProducer;
import static by.epam.lab.utils.Constants.*;

public class Runner {
    public static void main(String[] args) {
        TrialBuffer trialBuffer = new TrialBuffer();
        (new Thread(new TrialProducer(trialBuffer,CSV_NAME))).start();
        (new Thread(new TrialConsumer(trialBuffer))).start();
    }
}

package by.epam.lab.threads;

import by.epam.lab.beans.Trial;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import static by.epam.lab.utils.Constants.*;

public class TrialConsumer implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(TrialConsumer.class.getName());
    private final Queue<Trial> trialQueue;
    private final BlockingQueue<String> stringBlockingQueue;

    public TrialConsumer(Queue<Trial> trialQueue,
                         BlockingQueue<String> stringBlockingQueue) {
        this.trialQueue = trialQueue;
        this.stringBlockingQueue = stringBlockingQueue;
    }

    @Override
    public void run() {

        while (true) {
            String strTrial = EMPTY_STRING;
            try {
                strTrial = stringBlockingQueue.take();
                System.out.println(strTrial);
            } catch (InterruptedException ignored) {
                LOGGER.log(Level.WARNING, ignored.getMessage());
                continue;
            }
            if (DONE.equals(strTrial)) {
                break;
            }
            Trial trial = new Trial(strTrial.split(DELIMITER));
            System.out.println(trial);
            if (trial.isPassed()) {
                trialQueue.add(trial);
            }
        }
    }
}

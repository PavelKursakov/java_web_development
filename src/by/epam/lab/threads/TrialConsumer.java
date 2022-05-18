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
    private int id;


    public TrialConsumer(Queue<Trial> trialQueue,
                         BlockingQueue<String> stringBlockingQueue, int id) {
        this.trialQueue = trialQueue;
        this.stringBlockingQueue = stringBlockingQueue;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String strTrial = stringBlockingQueue.take();
                if (strTrial.equals(DONE)){
                    break;
                }
                Trial trial = new Trial(strTrial.split(DELIMITER));
                System.out.println(trial);
                if (trial.isPassed()) {
                    trialQueue.add(trial);
                }
            }
            System.out.println("Consumer " + id + " FINISH");
        } catch (InterruptedException e) {
            //Thread will not be Interrupted!
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }
}

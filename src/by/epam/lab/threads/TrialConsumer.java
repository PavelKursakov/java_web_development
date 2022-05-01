package by.epam.lab.threads;

import by.epam.lab.beans.Trial;

import java.util.concurrent.BlockingQueue;

import static by.epam.lab.utils.Constants.*;

public class TrialConsumer implements Runnable {
    private final BlockingQueue<Trial> trialBlockingQueue;
    private final BlockingQueue<String> stringBlockingQueue;

    public TrialConsumer(BlockingQueue<Trial> trialBlockingQueue,
                         BlockingQueue<String> stringBlockingQueue) {
        this.trialBlockingQueue = trialBlockingQueue;
        this.stringBlockingQueue = stringBlockingQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String strTrial = stringBlockingQueue.take();
                if (strTrial.isEmpty()) {
                    return;
                }
                Trial trial = new Trial(strTrial.split(DELIMITER));
                if (trial.isPassed()) {
                    trialBlockingQueue.put(trial);
                }
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}

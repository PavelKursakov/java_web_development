package by.epam.lab.threads;

import by.epam.lab.beans.Trial;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

import static by.epam.lab.utils.Constants.*;

public class TrialConsumer implements Runnable {
    private final BlockingQueue<Trial> trialBlockingQueue;
    private final BlockingQueue<String> stringBlockingQueue;
    private CountDownLatch stop;

    public TrialConsumer(BlockingQueue<Trial> trialBlockingQueue,
                         BlockingQueue<String> stringBlockingQueue, CountDownLatch stop) {
        this.trialBlockingQueue = trialBlockingQueue;
        this.stringBlockingQueue = stringBlockingQueue;
        this.stop = stop;
    }

    @Override
    public void run() {
        try {
            while (stop.getCount() != 0) {
                String strTrial = stringBlockingQueue.take();
                Trial trial = new Trial(strTrial.split(DELIMITER));
                if (trial.isPassed()) {
                    trialBlockingQueue.put(trial);
                }
                stop.countDown();
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}

package by.epam.lab.threads;

import by.epam.lab.beans.Trial;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

import static by.epam.lab.utils.Constants.*;

public class TrialConsumer implements Runnable {
    private int number;

    private final BlockingQueue<Trial> trialBlockingQueue;
    private final BlockingQueue<String> stringBlockingQueue;
    private CountDownLatch stop;

    public TrialConsumer(BlockingQueue<Trial> trialBlockingQueue,
                         BlockingQueue<String> stringBlockingQueue, CountDownLatch stop, int number) {
        this.trialBlockingQueue = trialBlockingQueue;
        this.stringBlockingQueue = stringBlockingQueue;
        this.stop = stop;
        this.number = number;
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

                System.out.println("Thread number " + number + " " + strTrial);
                stop.countDown();
                System.out.println("Thread number " + number + " Count down is " + stop.getCount());
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}

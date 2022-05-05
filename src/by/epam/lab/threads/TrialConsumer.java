package by.epam.lab.threads;

import by.epam.lab.beans.Trial;

import java.util.concurrent.BlockingQueue;

import static by.epam.lab.utils.Constants.*;

public class TrialConsumer implements Runnable {
    private final BlockingQueue<Trial> trialBlockingQueue;
    private final BlockingQueue<String> stringBlockingQueue;
    private StringBuilder sb;

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
                if (strTrial.equals("DONE")){
                    break;
                }
                Trial trial = new Trial(strTrial.split(DELIMITER));
                System.out.println(strTrial);
                if (trial.isPassed()) {
                    trialBlockingQueue.put(trial);
                }
            }
            System.out.println("FINISH");
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}

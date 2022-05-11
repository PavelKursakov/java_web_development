package by.epam.lab.threads;

import by.epam.lab.beans.Trial;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static by.epam.lab.utils.Constants.*;

public class TrialConsumer implements Runnable {
    private final ConcurrentLinkedQueue<Trial> linkedQueue;
    private final BlockingQueue<String> stringBlockingQueue;


    public TrialConsumer(ConcurrentLinkedQueue<Trial> linkedQueue,
                         BlockingQueue<String> stringBlockingQueue) {
        this.linkedQueue = linkedQueue;
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
                    linkedQueue.add(trial);
                }
            }
            System.out.println("FINISH");
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}

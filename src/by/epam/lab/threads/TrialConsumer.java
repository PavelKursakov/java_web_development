package by.epam.lab.threads;

import by.epam.lab.beans.Trial;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;

import static by.epam.lab.utils.Constants.*;

public class TrialConsumer implements Runnable {
    private final Queue<Trial> linkedQueue;
    private final BlockingQueue<String> stringBlockingQueue;
    private int id;


    public TrialConsumer(Queue<Trial> linkedQueue,
                         BlockingQueue<String> stringBlockingQueue, int id) {
        this.linkedQueue = linkedQueue;
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
                    linkedQueue.add(trial);
                }
            }
            System.out.println("Consumer " + id + " FINISH");
        } catch (InterruptedException e) {
            //Thread will not be Interrupted!
        }
    }
}

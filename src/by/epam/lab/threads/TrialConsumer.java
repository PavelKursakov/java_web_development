package by.epam.lab.threads;

import by.epam.lab.beans.Trial;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import static by.epam.lab.utils.Constants.*;

public class TrialConsumer implements Runnable {
    private final List<Trial> copyOnWriteArrayList;
    private final BlockingQueue<String> stringBlockingQueue;

    public TrialConsumer(List<Trial> copyOnWriteArrayList,
                         BlockingQueue<String> stringBlockingQueue) {
        this.copyOnWriteArrayList = copyOnWriteArrayList;
        this.stringBlockingQueue = stringBlockingQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int i = 0;
                String strTrial = stringBlockingQueue.take();
                if (strTrial.equals("DONE")){
                    break;
                }
                Trial trial = new Trial(strTrial.split(DELIMITER));
                System.out.println(strTrial);
                if (trial.isPassed()) {
                    copyOnWriteArrayList.add(trial);
                }
            }
            System.out.println("FINISH");
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}

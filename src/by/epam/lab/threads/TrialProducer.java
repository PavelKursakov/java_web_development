package by.epam.lab.threads;

import by.epam.lab.utils.Constants;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrialProducer implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(TrialProducer.class.getName());
    private final BlockingQueue<String> strQueue;
    private final CountDownLatch latch;
    private final String csvName;

    public TrialProducer(BlockingQueue<String> queue, CountDownLatch latch, String csvName) {
        this.strQueue = queue;
        this.latch = latch;
        this.csvName = csvName;
    }

    @Override
    public void run() {
        try (Scanner sc = new Scanner(new FileReader(csvName))) {
            while (sc.hasNextLine()) {
                System.out.println("Producer");
                try {
                    strQueue.put(sc.next());
                } catch (InterruptedException e) {
                    //Thread will not be Interrupted while waiting!
                    LOGGER.log(Level.WARNING, e.getMessage());
                }
            }
            System.out.println(Constants.PRODUCER_FINISH);
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } finally {
            latch.countDown();
        }
    }
}

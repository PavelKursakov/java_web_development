package by.epam.lab.threads;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public class TrialProducer implements Runnable {
    private final BlockingQueue<String> strQueue;
    private final CountDownLatch latch;
    private String csvName;

    public TrialProducer(BlockingQueue<String> queue, CountDownLatch latch, String csvName) {
        this.strQueue = queue;
        this.latch = latch;
        this.csvName = csvName;
    }

    @Override
    public void run() {
        try (Scanner sc = new Scanner(new FileReader(csvName))) {
            while (sc.hasNextLine()) {
                strQueue.put(sc.next());
            }
            latch.countDown();
            System.out.println("PRODUCER IS FINISHED");
        } catch (FileNotFoundException | InterruptedException e) {
            System.err.println(e);
        }
    }
}

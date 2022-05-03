package by.epam.lab.threads;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;


public class TrialProducer implements Runnable {
    private final BlockingQueue<String> strQueue;
    private String csvName;

    public TrialProducer(BlockingQueue<String> queue, String csvName) {
        this.strQueue = queue;
        this.csvName = csvName;
    }

    @Override
    public void run() {
        try (Scanner sc = new Scanner(new FileReader(csvName))) {
            Thread.sleep(500);
            while (sc.hasNextLine()) {
                strQueue.put(sc.next());
            }
        } catch (FileNotFoundException | InterruptedException e) {
            System.err.println(e);
        }
    }
}

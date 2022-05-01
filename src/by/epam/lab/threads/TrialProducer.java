package by.epam.lab.threads;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

import static by.epam.lab.utils.Constants.*;

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
            while (sc.hasNextLine()) {
                strQueue.put(sc.next());
            }
        } catch (FileNotFoundException | InterruptedException e) {
            System.err.println(e);
        } finally {
            try {
                strQueue.put(EMPTY_STRING);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}

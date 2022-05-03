package by.epam.lab.threads;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static by.epam.lab.utils.Constants.*;

public class TrialProducer implements Runnable {
    private final BlockingQueue<String> strQueue;
    private String csvName;

    private CountDownLatch stop;
    private int number;

    public TrialProducer(BlockingQueue<String> queue, String csvName, CountDownLatch stop, int number) {
        this.strQueue = queue;
        this.csvName = csvName;
        this.stop = stop;
        this.number = number;
    }

    @Override
    public void run() {
//        Lock lock = new ReentrantLock();
        try (Scanner sc = new Scanner(new FileReader(csvName))) {
//            lock.lockInterruptibly();
            while (sc.hasNextLine()) {
                strQueue.put(sc.next());
            }
        } catch (FileNotFoundException | InterruptedException e) {
            System.err.println(e);
        } finally {
//            lock.unlock();
//            try {
//                strQueue.put(EMPTY_STRING);
//            } catch (InterruptedException e) {
//                System.err.println(e.getMessage());
//            }
        }
    }
}

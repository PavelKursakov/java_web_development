package by.epam.lab.threads;

import by.epam.lab.beans.Trial;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static by.epam.lab.utils.Constants.*;

public class TrialWriter implements Runnable {
    private final BlockingQueue<Trial> trialBlockingQueue;
    private final BufferedWriter bufferedWriter;
    private CountDownLatch latch;
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public TrialWriter(BlockingQueue<Trial> trialBlockingQueue,
                       BufferedWriter bufferedWriter,
                       CountDownLatch latch) {
        this.trialBlockingQueue = trialBlockingQueue;
        this.bufferedWriter = bufferedWriter;
        this.latch = latch;
    }

    @Override
    public void run() {
//        lock.lock();
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    if (trialBlockingQueue.remainingCapacity() == 0) {
                        latch.countDown();
                        writeInto();
                    }
                }
                writeInto();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
            finally {
//                lock.unlock();
            }

    }

    private void writeInto() throws IOException {
        for (Trial element : trialBlockingQueue) {
            bufferedWriter.write(element + TABULATION);
        }
        bufferedWriter.flush();
        trialBlockingQueue.clear();
    }
}

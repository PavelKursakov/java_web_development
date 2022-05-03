package by.epam.lab.threads;

import by.epam.lab.beans.Trial;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

import static by.epam.lab.utils.Constants.*;

public class TrialWriter implements Runnable {
    private final BlockingQueue<Trial> trialBlockingQueue;
    private final BufferedWriter bufferedWriter;
    private CountDownLatch latch;

    public TrialWriter(BlockingQueue<Trial> trialBlockingQueue,
                       BufferedWriter bufferedWriter,
                       CountDownLatch latch) {
        this.trialBlockingQueue = trialBlockingQueue;
        this.bufferedWriter = bufferedWriter;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            while (latch.getCount() != 0) {
                if (trialBlockingQueue.remainingCapacity() == 0) {
                    latch.countDown();
                    writeInto();
                }
            }
            writeInto();
        } catch (IOException e) {
            System.err.println(e.getMessage());
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

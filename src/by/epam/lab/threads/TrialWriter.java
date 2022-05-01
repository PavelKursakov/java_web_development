package by.epam.lab.threads;

import by.epam.lab.beans.Trial;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;

import static by.epam.lab.utils.Constants.*;

public class TrialWriter implements Runnable {
    private final BlockingQueue<Trial> trialBlockingQueue;
    private final BufferedWriter bufferedWriter;
    private final Lock locker;

    public TrialWriter(BlockingQueue<Trial> trialBlockingQueue, BufferedWriter bufferedWriter, Lock locker) {
        this.trialBlockingQueue = trialBlockingQueue;
        this.bufferedWriter = bufferedWriter;
        this.locker = locker;
    }

    @Override
    public void run() {
            try {
                locker.lock();
                while (!Thread.currentThread().isInterrupted()) {
                    if (trialBlockingQueue.remainingCapacity() == 0) {
                        writeInto();
                    }
                }
                locker.unlock();
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

package by.epam.lab.threads;

import by.epam.lab.beans.Trial;

import java.io.BufferedWriter;
import java.io.IOException;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

import static by.epam.lab.utils.Constants.*;

public class TrialWriter implements Runnable {
    private final ConcurrentLinkedQueue<Trial> linkedQueue;
    private final CountDownLatch latch;
    private final BufferedWriter bufferedWriter;

    public TrialWriter(ConcurrentLinkedQueue<Trial> linkedQueue,
                       CountDownLatch latch, BufferedWriter bufferedWriter) {
        this.linkedQueue = linkedQueue;
        this.latch = latch;
        this.bufferedWriter = bufferedWriter;
    }

    @Override
    public void run() {
        try {
            latch.await();
            System.out.println("WRITER STARTED");
            while (linkedQueue.size() > 0) {
                Trial trial = linkedQueue.poll();
                if(trial != null) {
                    bufferedWriter.write(trial + TABULATION);
                    bufferedWriter.flush();
                }
            }
            System.out.println("WRITER IS FINISHED");
        } catch (IOException | InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}
